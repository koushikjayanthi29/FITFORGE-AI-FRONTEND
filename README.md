# React + Vite

## Deploying on Render

The root `render.yaml` defines three free-tier Render resources:

- `fitforge-web` serves the Vite frontend.
- `fitforge-api` runs the Spring Boot backend.
- `fitforge-db` is the PostgreSQL database.

The frontend and backend use Render service references for the API URL, CORS
origin, and database connection. Render generates `JWT_SECRET` for the backend;
do not put API keys or deployment secrets in this repository.

Install the Render CLI from https://render.com/docs/cli, then run `render login`
in your own terminal and approve the browser prompt. Never send your Render API
key in chat. Render's free Postgres plan has limited storage and retention; use
a paid plan before relying on this database for production data.

For local development, the frontend defaults to `http://localhost:8080`; the
backend defaults to a local PostgreSQL instance on port 5432. Set `JWT_SECRET`
in the backend environment before starting it.

This template provides a minimal setup to get React working in Vite with HMR and some Oxlint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Oxc](https://oxc.rs)
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/)

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the Oxlint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and Oxlint's TypeScript related rules in your project.
