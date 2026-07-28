# spring-learn — JWT Authentication with Spring Security

Secures the REST services with Spring Security (HTTP Basic + in-memory users)
and JSON Web Tokens.

## JWT process flow (implemented)
1. Client sends username/password → `GET /authenticate` (HTTP Basic).
2. Server validates, creates a **JWT** and returns `{ "token": "..." }`.
3. Client attaches the token: `Authorization: Bearer <token>` on later requests.
4. `JwtAuthorizationFilter` validates the token on every request.

## Components
| Class | Role |
|---|---|
| `SecurityConfig` | in-memory users `admin`/`user` (pwd `pwd`, BCrypt); HTTP Basic; authorization rules; registers the JWT filter |
| `AuthenticationController` | `GET /authenticate` — decodes Basic header, builds a signed JWT (20-min expiry) |
| `JwtAuthorizationFilter` | validates `Bearer` tokens and sets the SecurityContext |
| `JwtConstants` | shared HS256 signing key + expiry |
| `CountryController` | `GET /countries` — protected resource |

## Verified live (curl)
```
GET  /countries (no auth)                     -> HTTP 401
GET  /authenticate  -u user:pwd               -> {"token":"eyJhbGciOiJIUzI1NiJ9...."}
GET  /countries -H "Authorization: Bearer <token>"
                                              -> [{"code":"US",...},{"IN India"},...]  (200)
GET  /countries  (tampered token)             -> HTTP 401
GET  /authenticate  -u admin:pwd              -> HTTP 200 (both roles may authenticate)
```

## Tests
```bash
mvn test
```
`SpringLearnApplicationTests` (5 tests): no-auth → 401; `/authenticate` returns a
token; wrong password → 401; full flow (token → `/countries` → 200 with 4
countries); invalid token → 401.

## Notes — modernized for Spring Boot 3 / Security 6
The exercise targets the **removed** `WebSecurityConfigurerAdapter` API and
**jjwt 0.9.0** (`javax`). This project uses the current equivalents:
- **`SecurityFilterChain` bean** + `@EnableWebSecurity` (no adapter);
  in-memory users via `InMemoryUserDetailsManager`.
- **`OncePerRequestFilter`** for `JwtAuthorizationFilter` (Security 6 replacement
  for extending `BasicAuthenticationFilter`); **`jakarta.servlet`** not `javax`.
- **jjwt 0.12.6** — new builder/parser API (`signWith(key)`, `verifyWith(key)`,
  `parseSignedClaims`). HS256 now requires a **≥256-bit key**, so the secret is
  33 chars (the exercise's short `"secretkey"` only worked under 0.9.0).
- Authorization: `/authenticate` → `hasAnyRole("USER","ADMIN")` (Basic);
  `anyRequest().authenticated()` (JWT). The `/countries` role rule is commented
  out in the final config, per the exercise's last step.
- Spring Boot 3.4.1 / Java 17, `server.port=8083`.
