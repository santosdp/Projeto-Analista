<template>
  <div class="card-content column justify-evenly">
    <div class="card-login-item">
      <p class="text-body2">
        Inscreva-se utilizando seus dados pessoais e de funcionário do TJAM.
      </p>
    </div>

    <div class="card-login-item">
      <q-stepper
        v-model="registerStepper.step"
        ref="stepper"
        alternative-labels
        :contracted="isDense"
        color="primary"
        animated
        @before-transition="checkForm1"
      >
        <q-step
          :name="1"
          title="Dados pessoais"
          icon="face"
          :done="registerStepper.step > 1"
          :error="stateForm1"
        >
          <q-form ref="registerForm1">
            <q-input
              outlined
              :dense="isDense"
              label="Nome"
              type="text"
              v-model="registerForm.name"
              color="primary"
              :rules="[requiredField, matriculaValid]"
            ></q-input>

            <q-input
              outlined
              :dense="isDense"
              label="Matricula"
              type="number"
              v-model="registerForm.matricula"
              color="primary"
              :rules="[requiredField, matriculaValid]"
            ></q-input>

            <q-input
              outlined
              :dense="isDense"
              label="E-mail"
              type="email"
              v-model="registerForm.email"
              color="primary"
              :rules="[requiredField, emailValid]"
            ></q-input>
          </q-form>

          <q-stepper-navigation>
            <q-btn
              @click="
                () => {
                  registerStepper.done1 = true;
                  registerStepper.step = 2;
                }
              "
              color="primary"
              label="Continue"
            ></q-btn>
          </q-stepper-navigation>
        </q-step>

        <q-step
          :name="2"
          title="Contato"
          icon="smartphone"
          :done="registerStepper.step > 2"
        >
          <q-stepper-navigation>
            <q-btn
              @click="
                () => {
                  done2 = true;
                  step = 3;
                }
              "
              color="primary"
              label="Continue"
            ></q-btn>
            <q-btn
              flat
              @click="step = 1"
              color="primary"
              label="Back"
              class="q-ml-sm"
            ></q-btn>
          </q-stepper-navigation>
        </q-step>

        <q-step
          :name="3"
          title="Contato"
          icon="security"
          :done="registerStepper.step > 3"
        >
        </q-step>
      </q-stepper>
      <div class="row flex-center">
        <q-btn
          round
          color="white"
          text-color="primary"
          class="roundButton"
          icon="chevron_right"
        ></q-btn>
      </div>
    </div>
  </div>
</template>

<style>
.card-content {
  padding: 20px;
}

.card-login-item {
  max-width: 400px;
}

.q-input {
  border-radius: 8px;
}
.roundButton {
  border-radius: 50%;
}
</style>
<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'CardRegister',
  props: {
    buttonSize: String,
    isDense: Boolean,
  },

  setup() {
    const toggleButton = ref<string>('cadastrar');

    const registerStepper = {
      step: 1,
      done1: false,
      done2: false,
      done3: false,
      stateform1: false,
    };

    const registerForm = {
      name: '',
      matricula: 1,
      email: '',
    };

    return {
      toggleButton,
      registerStepper,
      registerForm,
    };
  },
  methods: {
    requiredField(val: string) {
      return !!val || 'Campo obrigatório';
    },
    matriculaValid(val: string) {
      return val.length === 12 || 'A matrícula deve possuir 12 caracteres';
    },
    emailValid(val: string) {
      const regex: RegExp = /^[a-zA-Z0-9._%+-]+@tjam\.jus\.br$/;
      return regex.test(val) || 'E-mail deve ser um endereço @tjam.jus.br';
    },

    checkForm1() {
      const registerForm1 = this.$refs.registerForm1 as unknown as {
        validate: () => Promise<boolean>;
      };
      if (registerForm1) {
        registerForm1.validate().then((success) => {
          if (success) {
            this.registerStepper.stateform1 = false;
          } else {
            this.registerStepper.stateform1 = true;
          }
        });
      }
    },
  },
});
</script>
