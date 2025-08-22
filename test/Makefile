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
	@docker-compose up -d postgres
	@echo "Waiting for PostgreSQL to be ready..."
	@sleep 10
	@echo "✅ PostgreSQL is ready at localhost:5432"

# Build all components
.PHONY: build
build:
	@echo "Building backend..."
	@cd backend && go mod tidy && go build -o bin/arisepreq-backend .
	@echo "Building frontend..."
	@cd frontend && echo "TODO: fix me - build frontend app"

# Clean build artifacts
.PHONY: clean
clean:
	@echo "Cleaning build artifacts..."
	@echo "🧹 Cleaning backend artifacts..."
	@rm -rf backend/bin/
	@rm -f backend/arisepreq-backend
	@rm -rf backend/coverage.out
	@rm -rf backend/coverage.html
	@echo "🧹 Cleaning frontend artifacts..."
	@rm -rf frontend/build/
	@rm -rf frontend/dist/
	@rm -rf frontend/coverage/
	@rm -rf frontend/.next/
	@echo "🧹 Cleaning Docker artifacts..."
	@docker image prune -f --filter label=project=arisepreq 2>/dev/null || true
	@echo "🧹 Cleaning test artifacts..."
	@rm -rf test-results/
	@rm -rf e2e/test-results/
	@rm -rf e2e/playwright-report/
	@echo "🧹 Cleaning temporary files..."
	@find . -name "*.tmp" -type f -delete 2>/dev/null || true
	@echo "✅ Build artifacts cleaned successfully"

# Build Docker images
.PHONY: docker-build
docker-build:
	@echo "Building Docker images..."

	@echo "Building backend image..."
	@docker build --no-cache -f ./backend/Dockerfile -t teamxyz-backend ./backend
	@echo "✅ Backend image built: teamxyz-backend"

	@echo "Building frontend image..."
	@docker build --no-cache -t teamxyz-frontend ./frontend
	@echo "✅ Frontend image built: teamxyz-frontend"
	@echo "✅ Docker images built successfully"

# Deploy to Kubernetes
.PHONY: k8s-deploy
k8s-deploy:
	@echo "Deploying to Kubernetes..."
	@kubectl apply -f deploy/namespace.yaml
	@kubectl apply -f deploy/secrets.yaml
	@kubectl apply -f deploy/backend-configmap.yaml
	@kubectl apply -f deploy/database-pvc.yaml
	@kubectl apply -f deploy/database-statefulset.yaml
	@kubectl apply -f deploy/database-service.yaml
	@kubectl apply -f deploy/frontend-deployment.yaml
	@kubectl apply -f deploy/frontend-service.yaml
	@echo "Waiting for database to be ready..."
	@kubectl wait --for=condition=ready pod -l app=postgres -n arisepreq --timeout=120s
	@echo "Deploying backend..."
	@kubectl apply -f deploy/backend-deployment.yaml
	@kubectl apply -f deploy/backend-service.yaml
	@echo "Waiting for backend to be ready..."
	@kubectl wait --for=condition=available deployment/backend-deployment -n arisepreq --timeout=120s
	@echo "Triggering backend deployment rollout to ensure latest image is used..."
	@kubectl rollout restart deployment/backend-deployment -n arisepreq
	@kubectl rollout status deployment/backend-deployment -n arisepreq --timeout=120s
	@echo "✅ Kubernetes deployment complete!"
	@echo "🌐 Backend available at: http://localhost:30090"
	@echo "🗄️  Database available at: localhost:30050"

# Clean Kubernetes resources
.PHONY: k8s-clean
k8s-clean:
	@echo "Cleaning Kubernetes resources..."
	@kubectl delete -f deploy/backend-service.yaml --ignore-not-found=true
	@kubectl delete -f deploy/backend-deployment.yaml --ignore-not-found=true
	@kubectl delete -f deploy/database-service.yaml --ignore-not-found=true
	@kubectl delete -f deploy/database-statefulset.yaml --ignore-not-found=true
	@kubectl delete -f deploy/database-pvc.yaml --ignore-not-found=true
	@kubectl delete -f deploy/backend-configmap.yaml --ignore-not-found=true
	@kubectl delete -f deploy/secrets.yaml --ignore-not-found=true
	@kubectl delete -f deploy/namespace.yaml --ignore-not-found=true
	@echo "✅ Kubernetes resources cleaned"

# Backend commands
.PHONY: bed-run ## Run backend locally
bed-run:
	@echo "Starting backend server..."
	@cd backend && go run main.go

.PHONY: bed-build ## Build backend binary
bed-build:
	@echo "Building backend..."
	@cd backend && go mod tidy && go build -o bin/arisepreq-backend .

.PHONY: bed-test-cov ## Run backend tests with coverage
bed-test-cov:
	@echo "Running backend coverage tests..."
	@$(MAKE) db-setup-test
	@cd backend && go test -v -cover ./...

.PHONY: bed-test ## Run backend tests
bed-test:
	@echo "Running backend tests..."
	@$(MAKE) db-setup-test
	@cd backend && go test -v ./...


# Frontend commands
.PHONY: fed-test-cov
fed-test-cov:
	@echo "Running frontend tests..."
	@cd frontend && npm test -- --coverage --watchAll=false

# Run frontend tests
.PHONY: fed-test
fed-test:
	@echo "Running frontend tests..."
	@cd frontend && npm test -- --watchAll=false

# Run e2e
.PHONY: e2e
e2e:
	@echo "Running e2e tests..."
	@if [ ! -d node_modules/@playwright/test ]; then npm install --save-dev @playwright/test; fi
	@npx playwright install
	@npx playwright test e2e/


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
validate-payload-test:
	@echo "Running test validate payload..."
	@k6 run k6/05-api-apply-loans.validation.test.js --env BASE_URL=http://localhost:30090  --vus 1 --iterations 1

# Database commands
.PHONY: db-start ## Start PostgreSQL database locally
db-start:
	@echo "Starting PostgreSQL database..."
	@docker-compose up -d postgres
	@echo "Waiting for PostgreSQL to be ready..."
	@sleep 10
	@echo "✅ PostgreSQL is ready at localhost:5432"

.PHONY: db-stop ## Stop PostgreSQL database
db-stop:
	@echo "Stopping PostgreSQL database..."
	@docker-compose down

.PHONY: db-test ## Test database connection
db-test:
	@echo "Testing database connection..."
	@./scripts/test-db.sh

.PHONY: db-reset ## Reset database (drop and recreate)
db-reset:
	@echo "Resetting database..."
	@docker-compose down -v
	@docker-compose up -d postgres
	@sleep 15
	@echo "✅ Database reset complete"

.PHONY: db-logs ## Show database logs
db-logs:
	@docker-compose logs -f postgres

.PHONY: db-setup-test ## Setup test database
db-setup-test:
	@echo "Setting up test database..."
	@./scripts/setup-test-db.sh