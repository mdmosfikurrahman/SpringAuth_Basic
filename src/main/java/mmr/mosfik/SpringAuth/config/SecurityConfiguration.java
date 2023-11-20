package mmr.mosfik.SpringAuth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static mmr.mosfik.SpringAuth.model.user.Permission.*;
import static mmr.mosfik.SpringAuth.model.user.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    public static final String[] whiteListedRoutes = new String[]{
            "/api/v1/auth/**"
    };

    public static final String[] managementRoutes = new String[]{
            "/api/v1/management/**"
    };

    public static final String[] adminRoutes = new String[]{
            "/api/v1/admin/**"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(whiteListedRoutes)
                                .permitAll()

                                .requestMatchers(managementRoutes)
                                .hasAnyRole(ADMIN.name(), MANAGER.name())

                                .requestMatchers(GET, managementRoutes)
                                .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())

                                .requestMatchers(POST, managementRoutes)
                                .hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())

                                .requestMatchers(PUT, managementRoutes)
                                .hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())

                                .requestMatchers(DELETE, managementRoutes)
                                .hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

                                .requestMatchers(GET, adminRoutes)
                                .hasAuthority(ADMIN_READ.name())

                                .requestMatchers(POST, adminRoutes)
                                .hasAuthority(ADMIN_CREATE.name())

                                .requestMatchers(PUT, adminRoutes)
                                .hasAuthority(ADMIN_UPDATE.name())

                                .requestMatchers(DELETE, adminRoutes)
                                .hasAuthority(ADMIN_DELETE.name())

                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}