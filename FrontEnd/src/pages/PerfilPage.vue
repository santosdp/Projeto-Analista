<template>
  <q-page class="column items-center justify-start">
    <div class="q-pt-md q-px-lg row justify-start full-width">
      <p class="text-weight-bold text-h6 text-secondary">Perfil</p>
    </div>

    <div class="column items-center">
      <q-img
        src="../assets/01_Fachada_TJAM.jpg"
        alt="Banner de perfil TJAM"
        class="banner-perfil"
      />
      <q-avatar size="80px" class="avatar-perfil">
        <q-img
          src="https://cdn.quasar.dev/img/boy-avatar.png"
          alt="Avatar de perfil"
        />
      </q-avatar>
      <p
        class="text-weight-medium text-secondary text-body1 text-center"
        style="transform: translateY(-30px); max-width: 300px"
      >
        {{ nomeUsuario }}
      </p>

      <p
        class="text-weight-light text-secondary text-body2 text-center"
        style="transform: translateY(-30px); max-width: 300px"
      >
        {{ departamentoUsuario }}
      </p>
    </div>

    <q-form @submit.prevent="onSubmit" class="column justify-start q-mb-lg">
      <div class="row justify-end">
        <q-btn
          flat
          round
          color="secondary"
          icon="edit"
          @click="isReadonly = false"
        ></q-btn>
      </div>
      <div class="row justify-evenly items-center">
        <generic-input
          label="Nome"
          :model-value="nomeUsuario"
          input-type="text"
          :is-readonly="isReadonly"
          :is-dense="isDense"
          :rules="[requiredField, NomeValid]"
          class="input-perfil q-mx-sm"
        ></generic-input>

        <generic-input
          label="Cadastro de Pessoa Física (CPF)"
          v-model="cpfUsuario"
          input-type="text"
          mask="###.###.###-##"
          :is-dense="isDense"
          :is-readonly="isReadonly"
          @keypress="onlyNumbers"
          :rules="[requiredField, NomeValid]"
          class="input-perfil q-mx-sm"
        ></generic-input>
      </div>

      <div class="row justify-evenly items-center">
        <generic-input
          label="Data de Nascimento"
          :model-value="nascimentoUsuario"
          input-type="date"
          :hint="isReadonly ? ' ' : 'DD/MM/YYYY'"
          mask="##/##/####"
          :is-dense="isDense"
          :is-readonly="isReadonly"
          :rules="[requiredField, dataValid, idadeValid]"
          class="input-perfil q-mx-sm"
        ></generic-input>

        <generic-input
          label="Data de Admissão"
          :model-value="admissaoUsuario"
          input-type="date"
          hint=" "
          mask="##/##/####"
          :is-dense="isDense"
          :is-readonly="isReadonly"
          :rules="[]"
          class="input-perfil q-mx-sm"
        ></generic-input>

        <generic-input
          label="Telefone"
          v-model="telefoneUsuario"
          input-type="text"
          icon-prepend="call"
          :is-dense="isDense"
          :is-readonly="isReadonly"
          mask="(##)#####-####"
          @keypress="onlyNumbers"
          :rules="[requiredField, telefoneValid]"
        ></generic-input>
      </div>

      <div class="row justify-evenly items-center">
        <generic-input
          label="E-mail"
          :model-value="emailUsuario"
          input-type="email"
          :is-readonly="isReadonly"
          :is-dense="isDense"
          :rules="[requiredField, emailValid]"
          class="input-perfil q-mx-sm"
        ></generic-input>

        <generic-input
          label="E-mail de Recuperação"
          :model-value="emailsecundarioUsuario"
          input-type="email"
          :is-readonly="isReadonly"
          :is-dense="isDense"
          :rules="[requiredField, emailNormalValid]"
          class="input-perfil q-mx-sm"
        ></generic-input>
      </div>

      <div v-if="!isReadonly" class="row justify-evenly items-center">
        <generic-input
          label="Senha"
          :is-outlined="true"
          :is-readonly="isReadonly"
          v-model="senhaUsuario"
          :input-type="visiblePwd1 ? 'password' : 'text'"
          :icon-append="visiblePwd1 ? 'visibility_off' : 'visibility'"
          :visible-icon="visibleIcon1"
          :hint="
            isReadonly
              ? ' '
              : 'Ao menos 8 e no máximo 16 caracteres, letras maiúsculas e minúsculas, números e pelo menos um caractere especial, como ! @ #.'
          "
          :is-dense="isDense"
          :rules="[
            requiredField,
            senhaValid1,
            senhaValid2,
            senhaValid3,
            senhaValid4,
            senhaValid5,
          ]"
        ></generic-input>

        <generic-input
          label="Confirmar Senha"
          v-model="senhaConfirmUsuario"
          :is-readonly="isReadonly"
          :input-type="visiblePwd2 ? 'password' : 'text'"
          :icon-append="visiblePwd2 ? 'visibility_off' : 'visibility'"
          :visible-icon="visibleIcon2"
          :is-dense="isDense"
          :rules="[requiredField, confirmarValid]"
        ></generic-input>
      </div>

      <div v-if="!isReadonly" class="row justify-center">
        <q-btn
          label="Cancelar"
          @click="isReadonly = true"
          flat
          class="text-weight-bold q-mx-md"
          size="md"
          color="secondary"
        ></q-btn>
        <q-btn
          label="Confirmar"
          type="submit"
          class="text-weight-bold q-mx-md"
          size="md"
          color="secondary"
        ></q-btn>
      </div>
    </q-form>
  </q-page>
</template>

<style scoped>
.banner-perfil {
  width: 60vw;
  border-radius: 16px;
  height: 150px;
}

.avatar-perfil {
  border: 2px solid white;
  transform: translateY(-40px);
}

.q-form {
  width: 60vw;
}
.input-perfil {
  width: 250px;
}
@media screen and (max-width: 600px) {
  .banner-perfil {
    width: 80vw;
    height: 100px;
  }
  .q-form {
    width: 80vw;
  }
}
</style>
<script lang="ts">
import GenericInput from 'src/components/GenericInput.vue';
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'PerfilPage',

  props: {
    isDense: Boolean,
  },

  components: {
    GenericInput,
  },

  setup() {
    return {
      nomeUsuario: ref<string>('Daniel Pereira dos Santos'),
      matriculaUsuario: ref<string>('212121212121'),
      nascimentoUsuario: ref<string>('1999-01-01'),
      departamentoUsuario: ref<string>('Tecnologia da Informação'),
      emailUsuario: ref<string>('santosd@tjam.jus.br'),
      emailsecundarioUsuario: ref<string>('santosd@gmail.com'),
      telefoneUsuario: ref<string>('92955555555'),
      cpfUsuario: ref<string>('00000000000'),
      senhaUsuario: ref<string>(''),
      admissaoUsuario: ref<string>('2022-02-05'),
      senhaConfirmUsuario: ref<string>(''),

      isReadonly: ref<boolean>(true),
      visiblePwd1: ref<boolean>(true),
      visiblePwd2: ref<boolean>(true),
    };
  },

  methods: {
    onSubmit() {},
    requiredField(val: string) {
      return !!val || 'Campo obrigatório';
    },

    NomeValid(val: string) {
      return val.length >= 12 || 'O nome deve possuir ao menos 12 caracteres';
    },

    emailValid(val: string) {
      const regex: RegExp = /^[a-zA-Z0-9._%+-]+@tjam\.jus\.br$/;
      return regex.test(val) || 'E-mail deve ser um endereço @tjam.jus.br';
    },

    emailNormalValid(val: string) {
      const regex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return regex.test(val) || 'E-mail inválido';
    },

    telefoneValid(val: string) {
      const regex: RegExp = /^\(?\d{2}\)?[\s-]?\d{4,5}[\s-]?\d{4}$/;
      return regex.test(val) || 'Apenas telefones brasileiros são aceitos.';
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

    confirmarValid(val: string) {
      return val === this.senhaUsuario || 'As senhas devem ser igual.';
    },

    visibleIcon1() {
      this.visiblePwd1 = !this.visiblePwd1;
    },
    visibleIcon2() {
      this.visiblePwd2 = !this.visiblePwd2;
    },

    dataValid(val: string) {
      return (
        this.validaNascimento(val) || 'Data inválida. Use o formato dd/mm/yyyy.'
      );
    },

    idadeValid(val: string) {
      return this.validaIdade(val) || 'Idade inválida.';
    },

    validaNascimento(dateString: string): boolean {
      const [year, month, day] = dateString.split('-').map(Number);
      const date = new Date(year, month - 1, day);
      return (
        date.getFullYear() === year &&
        date.getMonth() === month - 1 &&
        date.getDate() === day
      );
    },

    validaIdade(dateString: string): boolean {
      if (!this.validaNascimento(dateString)) return false;

      const [year, month, day] = dateString.split('-').map(Number);
      const birthDate = new Date(year, month - 1, day);

      const today = new Date();
      const age = today.getFullYear() - birthDate.getFullYear();

      const hasHadBirthdayThisYear =
        today.getMonth() > birthDate.getMonth() ||
        (today.getMonth() === birthDate.getMonth() &&
          today.getDate() >= birthDate.getDate());

      return (age > 18 && age < 100) || (age === 18 && hasHadBirthdayThisYear);
    },

    onlyNumbers(event: KeyboardEvent) {
      const key = event.key;
      if (!/^[0-9]$/.test(key) && key !== 'Backspace' && key !== 'Tab') {
        event.preventDefault();
      }
    },
  },
});
</script>
