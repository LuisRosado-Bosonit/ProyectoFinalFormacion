package bosonit.formacion.appFinal.genericClasses.security;

import bosonit.formacion.appFinal.usuario.infraestructure.utils.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring()
					.antMatchers("/h2-console/**");
		}


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v0/usuario/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v0/usuario/register").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v0/reserva").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.GET).hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated();
    }
}

