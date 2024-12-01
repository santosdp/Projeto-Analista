<template>
  <div class="card-content column justify-evenly items-center">
    <div class="card-login-item">
      <p class="text-body2">
        Bem-vindo ao sistema de acesso do TJAM, para entrar na Intranet primeira
        você deve se identificar como funcionário.
      </p>
    </div>

    <div class="card-login-item column items-center">
      <q-form @submit.prevent="onSubmit">
        <generic-input
          label="E-mail"
          :is-outlined="true"
          v-model="emailInput"
          input-type="email"
          icon-prepend="mail"
          hint="Necessário utilizar seu e-mail TJAM (@tjam.jus.br)."
          :is-dense="isDense"
          :rules="[requiredField, emailValid]"
          class="input-login"
        ></generic-input>

        <generic-input
          label="Senha"
          :is-outlined="true"
          v-model="passwordInput"
          :input-type="visiblePwd ? 'password' : 'text'"
          :icon-append="visiblePwd ? 'visibility_off' : 'visibility'"
          :visible-icon="visibleIcon"
          hint="Ao menos 8 e no máximo 16 caracteres, letras maiúsculas e minúsculas, números e pelo menos um caractere especial, como ! @ #."
          :is-dense="isDense"
          class="input-login"
          :rules="[
            requiredField,
            senhaValid1,
            senhaValid2,
            senhaValid3,
            senhaValid4,
            senhaValid5,
          ]"
        ></generic-input>

        <div class="row items-center justify-end">
          <p class="q-pt-md"><a href="">Esqueceu sua senha?</a></p>
        </div>

        <q-btn
          label="Login"
          type="submit"
          class="full-width text-weight-bold"
          :size="buttonSize"
          color="primary"
        ></q-btn>
      </q-form>
    </div>
  </div>
</template>

<style scoped>
.card-content {
  padding: 20px;
}
.input-login {
  width: 100%;
}
.card-login-item {
  max-width: 400px;
}
.q-input {
  border-radius: 8px;
}

.q-btn {
  margin-top: 10px;
  margin-bottom: 10px;
  height: 40px;
  color: #f1f1f1;
  border-radius: 8px;
}
</style>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import GenericInput from './GenericInput.vue';
import { useAuthStore } from 'src/stores/auth';
import { loginUsuario } from 'src/services/authService';

export default defineComponent({
  name: 'CardLogin',

  props: {
    buttonSize: String,
    isDense: Boolean,
  },

  components: {
    GenericInput,
  },

  data() {
    return {
      windowWidth: window.innerWidth,
    };
  },

  setup() {
    return {
      emailInput: ref<string>(''),
      passwordInput: ref<string>(''),
      passwordVisible: ref<boolean>(false),

      visiblePwd: ref<boolean>(true),
    };
  },

  methods: {
    async onSubmit(): Promise<void> {
      const authStore = useAuthStore();
      try {
        const data = await loginUsuario(this.emailInput, this.passwordInput);
        authStore.login(data.tokenJWT);
        this.$q.notify({
          message: 'Login realizado!',
          color: 'positive',
          icon: 'check_circle_outline',
        });
        this.$router.push('home');
      } catch (error) {
        this.$q.notify({
          message: 'Erro ao realizar login. Tente novamente.',
          color: 'negative',
          icon: 'ti-close',
        });
      }
    },

    requiredField(val: string) {
      return !!val || 'Campo obrigatório';
    },

    emailValid(val: string) {
      const regex: RegExp = /^[a-zA-Z0-9._%+-]+@tjam\.jus\.br$/;
      return regex.test(val) || 'E-mail deve ser um endereço @tjam.jus.br';
    },

    senhaValid1(val: string) {
      const regex: RegExp = /[a-z]/;
      return (
        regex.test(val) || 'A senha deve conter ao menos uma letra minúscula.'
      );
    },
    senhaValid2(val: string) {
      const regex: RegExp = /[A-Z]/;
      return (
        regex.test(val) || 'A senha deve conter ao menos uma letra maiúscula.'
      );
    },
    senhaValid3(val: string) {
      const regex: RegExp = /\d/;
      return regex.test(val) || 'A senha deve conter ao menos um números.';
    },
    senhaValid4(val: string) {
      const regex: RegExp = /[!@#$%^&*(),.?":{}|<>]/;
      return (
        regex.test(val) || 'A senha deve conter ao menos um caractere especial.'
      );
    },
    senhaValid5(val: string) {
      return (
        (val.length >= 8 && val.length <= 16) ||
        'A senha deve conter 8 à 16 caracteres.'
      );
    },

    visibleIcon() {
      this.visiblePwd = !this.visiblePwd;
    },
  },
});
</script>
