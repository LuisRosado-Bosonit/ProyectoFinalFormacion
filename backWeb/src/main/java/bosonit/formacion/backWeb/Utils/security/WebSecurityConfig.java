package bosonit.formacion.backWeb.Utils.security;

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
    protected void configure(HttpSecurity http) throws Exception {			//TODO COMO PUEDO DIVIDIR ESTO EN VARIOS METODOS PARA QUE SEA MAS LEGIBLE Y LIGERO
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //.antMatchers(HttpMethod.POST, "/api/v0/reserva").permitAll()
				.antMatchers(HttpMethod.GET, "/api/v0/reserva").hasAnyRole("ADMIN","USER")
				.antMatchers(HttpMethod.GET, "/api/v0/reserva/getAll").hasAnyRole("ADMIN","USER")
				//.antMatchers( "/swagger-ui.html").permitAll()
                .anyRequest().authenticated();
    }
}

