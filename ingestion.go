package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"time"
)

const baseURL = "https://hml-dot-ppb-back-dot-dataservices-non-prod.appspot.com"

func makeGetRequest(endpoint, step string) {
	fullURL := baseURL + endpoint
	fmt.Printf("Iniciando %s - [GET] Endpoint: %s\n", step, fullURL)

	start := time.Now()
	resp, err := http.Get(fullURL)
	duration := time.Since(start).Seconds()

	if err != nil {
		fmt.Printf("❌ Erro ao executar o GET %s: %v\n", endpoint, err)
		return
	}
	defer resp.Body.Close()

	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		fmt.Printf("❌ Erro ao ler resposta de %s: %v\n", endpoint, err)
		return
	}

	if resp.StatusCode != 200 {
		fmt.Printf("❌ Falha na requisição %s\n🔹 Status Code: %d\n🔹 Resposta: %s\n", endpoint, resp.StatusCode, string(body))
		return
	}

	fmt.Printf("✅ Finalizado %s - Tempo de execução: %.2f segundos\n", step, duration)
	time.Sleep(5 * time.Second) // Aguarda 5 segundos antes da próxima chamada
}

func main() {
	fmt.Println("=== Iniciando processamento de ingestão ===")

	// Etapa 1: Setup
	makeGetRequest("/api/ingestion/schedule/setup", "Etapa 1: Setup")
	makeGetRequest("/api/ingestion/schedule/demand/interface", "Etapa 2: Interface de Demanda")
	processDemand()

	// Etapa 3: Interface de Custo
	makeGetRequest("/api/ingestion/schedule/cost-update", "Etapa 3: Interface de Custo")
	processPartNumbers()	

	fmt.Println("✅ === Processo completo. ===")
}

func processDemand() {
	// currentYear := time.Now().Year()
	years := []int{2024, 2025, 2026}
	for _, year := range years {
		fmt.Printf("\n=== Iniciando processamento para o ano: %d ===\n", year)

		makeGetRequest(fmt.Sprintf("/api/ingestion/schedule/demand/%d", year), fmt.Sprintf("Etapa 4: Criando Demanda e Produtos da Demanda - Ano %d", year))
		makeGetRequest(fmt.Sprintf("/api/ingestion/schedule/demand/commodities/create/%d", year), fmt.Sprintf("Etapa 5: Criando PartNumbers e Commodities - Ano %d", year))
		makeGetRequest(fmt.Sprintf("/api/ingestion/schedule/demand/commodities/populate/%d", year), fmt.Sprintf("Etapa 6: Preenchendo PartNumbers e Commodities - Ano %d", year))
		makeGetRequest("/api/ingestion/data-processing/business-case-processing/automatic-allocation", "Etapa 7: Alocação automática")

		fmt.Printf("=== Finalizado processamento para o ano: %d ===\n", year)
	}
}

func processPartNumbers() {
	fmt.Println("\n=== Iniciando processamento de PartNumbers ===")
	years := []int{2024, 2025, 2026}
	locations := []int{1, 2}
	months := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}

	for _, location := range locations {
		for _, year := range years  {
			for _, month := range months {
				endpoint := fmt.Sprintf("/api/ingestion/data-processing/cost-update-processing/partnumbers/%d/%d/%d", year, month, location)
				step := fmt.Sprintf("Processamento PartNumbers - Ano: %d, Mês: %d, Local: %d", year, month, location)
				makeGetRequest(endpoint, step)
			}
		}
	}
	fmt.Println("✅ === Processamento de PartNumbers completo. ===")
}