import { execSync } from 'child_process';

console.time('DeployTime');
try {
  execSync('gcloud app deploy app.hml.yaml --no-promote --version=hml', { stdio: 'inherit' });
  console.timeEnd('DeployTime');
} catch (error) {
  console.error('Deploy failed:', error);
  process.exit(1);
}
