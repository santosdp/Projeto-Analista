import { jwtDecode, JwtPayload } from 'jwt-decode';
import { defineStore } from 'pinia';
import { api } from 'src/boot/axios';

interface AuthState {
  token: string | null;
  user: JwtPayload | null;
  userData: Usuario | null;
}

interface Usuario {
  id: number | null;
  name: string | null;
  matricula: number | null;
  cpf: string | null;
  email: string | null;
  email_recuperacao: string | null;
  data_nascimento: string | null;
  cargo: string | null;
}

export const useAuthStore = defineStore('auth', {
  state: (): AuthState => ({
    token: null,
    user: null,
    userData: null,
  }),
  getters: {
    isTokenExpired(state): boolean {
      if (!state.token) return true;
      const decoded = jwtDecode<JwtPayload>(state.token);
      const currentTime = Date.now() / 1000;
      return decoded.exp ? decoded.exp < currentTime : true;
    },
    userId(state): string | null {
      return state.user?.sub || null;
    },
  },
  actions: {
    async initializeAuth() {
      const token = localStorage.getItem('token');
      if (token && !this.isTokenExpired) {
        this.token = token;
        this.user = jwtDecode<JwtPayload>(token);
        await this.fetchUserData();
      } else {
        this.logout();
      }
    },
    async login(token: string) {
      this.token = token;
      this.user = jwtDecode<JwtPayload>(token);
      localStorage.setItem('token', token);
      await this.fetchUserData();
    },
    logout() {
      this.token = null;
      this.user = null;
      this.userData = null;
      localStorage.removeItem('token');
    },
    async fetchUserData() {
      try {
        const userId = this.userId;
        if (userId) {
          const response = await api.get(`/usuario/${userId}`);
          this.userData = response.data;
        }
      } catch (error) {
        console.error('Erro ao obter os dados do usuário:', error);
      }
    },
  },
});
