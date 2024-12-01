package com.backend.ponto_eletronico.config.security;

import com.backend.ponto_eletronico.model.Usuario;
import com.backend.ponto_eletronico.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var tokenJWT = recuperarToken(request);

    if(tokenJWT != null) {
      var subject = tokenService.getSubject(tokenJWT);
      Usuario usuario_long = usuarioRepository.findById(subject).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
      var usuario = usuarioRepository.findByEmail(usuario_long.getEmail());
      var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String recuperarToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if(authorizationHeader != null) {
      return authorizationHeader.replace("Bearer ", "");
    }
    return null;

  }
}
