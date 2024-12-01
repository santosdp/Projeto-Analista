import axios from 'axios';
import { api } from 'src/boot/axios';

export const loginUsuario = async (login: string, senha: string) => {
  try {
    const response = await api.post('/login', { login, senha });
    if (response.status === 200) {
      return response.data;
    } else {
      throw new Error('Login falhou.');
    }
  } catch (error) {
    throw new Error(handleError(error));
  }
};

export const listarDepartamentos = async (): Promise<Departamento[]> => {
  try {
    const response = await api.get<Departamento[]>('/departamento/listar');
    if (response.status === 200) {
      return response.data;
    } else {
      throw new Error('Falha ao listar departamentos.');
    }
  } catch (error) {
    throw new Error(handleError(error));
  }
};

export const criarUsuario = async (
  nome: string,
  matricula: number,
  email: string,
  email_recuperacao: string,
  telefone: string,
  senha: string,
  nomedepartamento: string,
  cpf: string,
  data_nascimento: string
) => {
  try {
    const response = await api.post('/usuario/criar', {
      nome,
      matricula,
      email,
      email_recuperacao,
      telefone,
      senha,
      nomedepartamento,
      cpf,
      data_nascimento,
    });

    if (response.status === 201) {
      return response.data;
    } else {
      throw new Error('Falha ao criar o usuário.');
    }
  } catch (error) {
    throw new Error(handleError(error));
  }
};

const handleError = (error: unknown): string => {
  if (axios.isAxiosError(error)) {
    if (error.response) {
      switch (error.response.status) {
        case 400:
          return 'Credenciais inválidas.';
        case 401:
          return 'Não autorizado. Verifique suas credenciais.';
        case 403:
          return 'Acesso proibido.';
        case 404:
          return 'Endpoint não encontrado.';
        case 500:
          return 'Erro interno no servidor. Tente novamente mais tarde.';
        default:
          return 'Erro desconhecido.';
      }
    } else {
      return 'Erro ao conectar ao servidor.';
    }
  }
  return 'Erro inesperado.';
};

interface Departamento {
  id_departamento: number;
  nome_departamento: string;
  nome_gerente: string;
}
