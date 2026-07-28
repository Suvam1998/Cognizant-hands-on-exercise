# Centralized Authentication & SSO with Spring Boot 3

Three self-contained Spring Boot 3 projects covering OAuth 2.1/OIDC login, a JWT
resource server, and a custom JWT filter.

> **Important — modernized for Spring Boot 3.**
> The exercise's sample code targets the pre-Boot-3 API
> (`WebSecurityConfigurerAdapter`, `http.authorizeRequests()`, jjwt `0.9.1`),
> **which was removed in Spring Security 6 and does not compile on Spring Boot 3**.
> These projects implement the same scenarios with the current API:
> `SecurityFilterChain` beans + the lambda DSL, and jjwt `0.12.x`. The intent of
> each exercise is unchanged.

## Projects
| # | Folder | Scenario | Run |
|---|---|---|---|
| 1 | `Exercise1_OAuth2Login` | Centralized auth via OAuth2/OIDC login (`oauth2Login`) | `mvn test` |
| 2 | `Exercise2_ResourceServer` | Resource server validating Bearer JWTs (`oauth2ResourceServer().jwt()`) | `mvn test` |
| 3 | `Exercise3_JWT` | Custom `OncePerRequestFilter` + `JwtTokenProvider` issuing/validating JWTs | `mvn test` |

Each project is an independent Maven module — `cd` into it and run `mvn test`.

## Exercise 1 — OAuth2/OIDC Login
- `SecurityConfig` requires authentication for all requests and enables
  `oauth2Login`; `application.yml` configures the client registration + provider
  (Google URIs, placeholder client-id/secret).
- Tests: context loads, an unauthenticated request is redirected to
  `/oauth2/authorization/my-client`, and a simulated OIDC login reaches `/user`.
- To perform a real login, drop in a real client-id/secret from your provider.

## Exercise 2 — Resource Server (JWT)
- `ResourceServerConfig` secures all endpoints behind `oauth2ResourceServer.jwt`.
- In production you'd set `spring.security.oauth2.resourceserver.jwt.issuer-uri`
  and the keys are fetched from the authorization server. To keep the sample
  runnable **offline**, a symmetric (HS256) `JwtDecoder` bean is provided (the
  issuer-based auto-config backs off when a `JwtDecoder` bean exists).
- Tests: no token → 401; a valid HS256-signed token → 200.

## Exercise 3 — Custom JWT Filter
- `JwtTokenProvider` — `createToken` / `validateToken` / `getAuthentication`
  (the sample's provider was incomplete; filled in with jjwt 0.12.x).
- `JwtTokenFilter` (jakarta.servlet) populates the `SecurityContext` from a
  Bearer token; `SecurityConfig` adds it before `UsernamePasswordAuthenticationFilter`
  and makes `/auth/**` public.
- `POST /auth/login?username=alice` → `{ "token": "..." }`; `GET /secure` with
  that Bearer token → greeting.
- Tests: login→token→secure (200); missing/invalid token → 403.

## Requirements
- JDK 17+ (verified on JDK 24), Maven 3.9+, internet access for the first
  dependency download.
