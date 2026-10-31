# BelaDent backend production deployment

The repository contains a Dockerfile and a Render Blueprint in `render.yaml`.

Before the first deployment, Render will ask for these secret values:

- `ADMIN_USERNAME`
- `ADMIN_EMAIL`
- `ADMIN_PASSWORD`

Use a unique strong password. Never commit real credentials to Git.

The Blueprint also creates PostgreSQL, generates the JWT secret, configures the production CORS allowlist and mounts persistent storage at `/var/data` so uploaded images survive restarts and deployments.

After deployment:

1. Confirm `https://api.beladent.ge/api/public/health` returns a healthy response.
2. Sign in through `https://beladent.ge/Security` and immediately verify the administrator account.
3. Upload a test image, restart the backend service and confirm the image remains available.
