
# Default target
.PHONY: help
help:
	@echo "ArisePreQ Loan Pre-Qualification System"
	@echo "Available commands:"
	@grep -E '^.PHONY:\s+[^#]*(##.*)?' $(MAKEFILE_LIST) | sort | cut -d ':' -f 2 | awk 'BEGIN {FS = "#"}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'


# Start the complete system
.PHONY: start
start:
	@echo "Starting ArisePreQ system..."
	@./scripts/start-system.sh

# Start local development
.PHONY: start-local
start-local:
	@echo "Starting local development..."
	@echo "TODO: fix me - you can docker-compose up here"

# Build all components
.PHONY: build
build:
	@echo "Building backend..."
	@cd backend && echo "TODO: fix me - build backend app"
	@echo "Building frontend..."
	@cd frontend && echo "TODO: fix me - build frontend app"

# Clean build artifacts
.PHONY: clean
clean:
	@echo "Cleaning build artifacts..."
	@echo "TODO: fix me - add steps to clean artifacts build here"

# Build Docker images
.PHONY: docker-build
docker-build:
	@echo "Building Docker images..."
	@echo "TODO: fix me -- add docker build backend image name teamXYZ-backend"
	@echo "TODO: fix me -- add docker build frontend image name teamXYZ-frontend"

# Deploy to Kubernetes
.PHONY: k8s-deploy
k8s-deploy:
	@echo "Deploying to Kubernetes..."
	@echo "TODO: fix me - add kubectl apply here from deploy/ folder"

# Clean Kubernetes resources
.PHONY: k8s-clean
k8s-clean:
	@echo "Cleaning Kubernetes resources..."
	@echo "TODO: fix me -- add kubectl delete to clean resource here"

# Backend commands
.PHONY: bed-test-cov
bed-test-cov:
	@echo "Running backend coverage tests..."
	@cd backend && echo "TODO: fix me"

# Run backend tests
.PHONY: bed-test
bed-test:
	@echo "Running backend tests..."
	@cd backend && echo "TODO: fix me"


# Frontend commands
.PHONY: fed-test-cov
fed-test-cov:
	@echo "Running frontend coverage tests..."
	@cd frontend && echo "TODO: fix me"

# Run frontend tests
.PHONY: fed-test
fed-test:
	@echo "Running frontend tests..."
	@cd frontend && echo "TODO: fix me"

# Run e2e
.PHONY: e2e
test:
	@echo "Running e2e tests..."
	@cd e2e && echo "TODO: fix me -- script run Robot framework or Playwright"


# Run k6 load tests
.PHONY: smoke-test
smoke-test:
	@echo "Running smoke tests..."
	@k6 run k6/01-api-apply-loans.smoke.test.js --env BASE_URL=http://localhost:30090 --vus 1 --iterations 1

.PHONY: load-test
load-test:
	@echo "Running load average tests..."
	@k6 run k6/02-api-apply-loans.average.test.js --env BASE_URL=http://localhost:30090

.PHONY: stress-test
stress-test:
	@echo "Running stress tests..."
	@k6 run k6/03-api-apply-loans.stress.test.js --env BASE_URL=http://localhost:30090

.PHONY: spike-test
spike-test:
	@echo "Running spike tests..."
	@k6 run k6/04-api-apply-loans.spike.test.js --env BASE_URL=http://localhost:30090

.PHONY: load-web-test
load-web-test:
	@echo "Running load web tests..."
	@k6 run k6/06-frontend-web.average.test.js --env FRONTEND_URL=http://localhost:30080

.PHONY: validate-payload-test
test-validate-payload:
	@echo "Running test validate payload..."
	@k6 run k6/05-api-apply-loans.validation.test.js --env BASE_URL=http://localhost:30090  --vus 1 --iterations 1
