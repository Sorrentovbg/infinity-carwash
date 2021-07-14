package ru.infinitycarwash.corelib.filters;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.infinitycarwash.corelib.entities.UserInfo;
import ru.infinitycarwash.corelib.exceptions.InvalidTokenException;
import ru.infinitycarwash.corelib.services.JWTTokenService;
import ru.infinitycarwash.corelib.services.RedisService;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTTokenService tokenService;

    RedisService redisService;


    public JWTAuthenticationFilter(JWTTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeaderIsInvalid(authorizationHeader)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if(redisService.checkToken(authorizationHeader)){
            throw new InvalidTokenException();
        }

        UsernamePasswordAuthenticationToken authenticationToken = createToken(authorizationHeader);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean authorizationHeaderIsInvalid(String authorizationHeader) {
        return authorizationHeader == null
                || !authorizationHeader.startsWith("Bearer ");
    }

    private UsernamePasswordAuthenticationToken createToken(String authorizationHeader) throws ExpiredJwtException {
        String token = authorizationHeader.replace("Bearer ", "");


        UserInfo userInfo = tokenService.parseToken(token);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (userInfo.getRole() != null && !userInfo.getRole().isEmpty()) {
            authorities.add(new SimpleGrantedAuthority(userInfo.getRole()));
        }
        return new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
    }
}
