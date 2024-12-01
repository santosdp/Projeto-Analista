import { jwtDecode, JwtPayload } from 'jwt-decode';

export const decodeToken = (token: string): JwtPayload | null => {
  try {
    return jwtDecode<JwtPayload>(token);
  } catch (error) {
    console.error('Erro ao decodificar o token:', error);
    return null;
  }
};

export const isTokenExpired = (token: string): boolean => {
  const decoded = decodeToken(token);
  if (!decoded) return true;
  return decoded.exp ? decoded.exp < Date.now() / 1000 : true;
};

export function getSubjectFromToken(token: string): number | null {
  try {
    const decoded = jwtDecode<{ sub: string }>(token);
    return Number(decoded.sub);
  } catch (error) {
    console.error('Erro ao decodificar o token JWT:', error);
    return null;
  }
}
