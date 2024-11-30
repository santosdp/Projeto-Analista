<template>
  <div class="card-content column justify-evenly">
    <div class="card-login-item">
      <p class="text-body2">
        Inscreva-se utilizando seus dados pessoais e de funcionário do TJAM.
      </p>
    </div>

    <div class="card-login-item">
      <q-stepper
        v-model="registerStepperStep"
        ref="stepper"
        contracted
        color="primary"
        animated
      >
        <q-step
          :name="1"
          title="Dados pessoais"
          icon="person"
          :done="registerStepperStep > 1"
          :error="registerStepperStateForm1"
        >
          <q-form ref="registerForm1" class="column items-center">
            <generic-input
              :is-outlined="true"
              label="Nome"
              v-model="registerFormName"
              input-type="text"
              icon-prepend="person"
              :is-dense="isDense"
              :rules="[requiredField, NomeValid]"
              class="input-register"
            ></generic-input>

            <generic-input
              label="Data de Nascimento"
              :is-outlined="true"
              :model-value="registerFormNascimento"
              input-type="date"
              hint="DD/MM/YYYY"
              mask="##/##/####"
              :is-dense="isDense"
              :rules="[requiredField, dataValid, idadeValid]"
              class="input-register"
            ></generic-input>

            <generic-input
              label="Cadastro de Pessoa Física (CPF)"
              :is-outlined="true"
              v-model="registerFormCPF"
              input-type="text"
              mask="###.###.###-##"
              icon-prepend="person"
              :is-dense="isDense"
              @keypress="onlyNumbers"
              :rules="[requiredField, NomeValid]"
              class="input-register"
            ></generic-input>
          </q-form>
        </q-step>

        <q-step
          :name="2"
          title="Contato"
          icon="smartphone"
          :done="registerStepperStep > 2"
          :error="registerStepperStateForm2"
        >
          <q-form ref="registerForm2" class="column items-center">
            <generic-input
              label="Matricula"
              :is-outlined="true"
              v-model="registerFormMatricula"
              input-type="text"
              :is-dense="isDense"
              mask="############"
              @keypress="onlyNumbers"
              :rules="[requiredField, matriculaValid]"
              class="input-register"
            ></generic-input>

            <q-field
              borderless
              stack-label
              label="Departamento"
              :dense="isDense"
              class="q-my-none input-register"
            >
              <template v-slot:control>
                <q-select
                  outlined
                  v-model="departamentoSelect"
                  :options="registerFormDepartamento"
                  :rules="[requiredField]"
                  class="full-width q-my-sm"
                />
              </template>
            </q-field>

            <generic-input
              label="E-mail"
              :is-outlined="true"
              v-model="registerFormEmail"
              input-type="email"
              icon-prepend="mail"
              hint="Necessário utilizar seu e-mail TJAM (@tjam.jus.br)."
              :is-dense="isDense"
              :rules="[requiredField, emailValid]"
              class="input-register"
            ></generic-input>

            <generic-input
              label="Telefone"
              :is-outlined="true"
              v-model="registerFormTelefone"
              input-type="text"
              icon-prepend="call"
              :is-dense="isDense"
              mask="(##)#####-####"
              @keypress="onlyNumbers"
              :rules="[requiredField, telefoneValid]"
              class="input-register"
            ></generic-input>
          </q-form>
        </q-step>

        <q-step
          :name="3"
          title="Contato"
          icon="security"
          :done="registerStepperStep > 3"
          :error="registerStepperStateForm3"
        >
          <q-form ref="registerForm3" class="column items-center">
            <generic-input
              label="E-mail de Recuperação"
              :is-outlined="true"
              v-model="registerFormEmailSecundario"
              input-type="email"
              icon-prepend="mail"
              hint="Necessário utilizar seu e-mail TJAM (@tjam.jus.br)."
              :is-dense="isDense"
              :rules="[requiredField, emailNormalValid]"
              class="input-register"
            ></generic-input>

            <generic-input
              label="Senha"
              :is-outlined="true"
              v-model="registerFormSenha"
              :input-type="visiblePwd1 ? 'password' : 'text'"
              :icon-append="visiblePwd1 ? 'visibility_off' : 'visibility'"
              :visible-icon="visibleIcon1"
              hint="Ao menos 8 e no máximo 16 caracteres, letras maiúsculas e minúsculas, números e pelo menos um caractere especial, como ! @ #."
              :is-dense="isDense"
              class="input-register"
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
              v-model="registerFormSenhaConfirm"
              :input-type="visiblePwd2 ? 'password' : 'text'"
              :icon-append="visiblePwd2 ? 'visibility_off' : 'visibility'"
              :visible-icon="visibleIcon2"
              :is-dense="isDense"
              :rules="[requiredField, confirmarValid]"
              class="input-register"
            ></generic-input>
          </q-form>
        </q-step>

        <template v-slot:navigation>
          <q-stepper-navigation class="row justify-evenly">
            <q-btn
              v-if="registerStepperStep > 1"
              @click="backStepper()"
              color="primary"
              flat
              icon="arrow_back"
              class="roundButton"
            ></q-btn>
            <q-btn
              @click="nextStepper()"
              color="primary"
              :icon="registerStepperStep === 3 ? 'check' : 'arrow_forward'"
              class="roundButton"
            ></q-btn>
          </q-stepper-navigation>
        </template>
      </q-stepper>
    </div>
    <p>{{ registerFormCPF }}</p>
  </div>
</template>

<style scoped>
.card-content {
  padding: 20px;
}

.card-login-item {
  max-width: 400px;
}
.input-register {
  width: 80vw;
  max-width: 250px;
}
.q-input {
  border-radius: 8px;
}
.roundButton {
  border-radius: 50%;
  width: 40px;
  height: 40px;
}
</style>
<script lang="ts">
import { QStepper } from 'quasar';
import { defineComponent, PropType, ref } from 'vue';
import GenericInput from './GenericInput.vue';

export default defineComponent({
  name: 'CardRegister',
  props: {
    buttonSize: String,
    isDense: Boolean,
    method: {
      type: Function as PropType<() => void>,
      required: true,
    },
  },

  components: {
    GenericInput,
  },

  setup() {
    return {
      registerStepperStep: ref<number>(1),
      registerStepperDone1: ref<boolean>(false),
      registerStepperDone2: ref<boolean>(false),
      registerStepperDone3: ref<boolean>(false),
      registerStepperStateForm1: ref<boolean>(false),
      registerStepperStateForm2: ref<boolean>(false),
      registerStepperStateForm3: ref<boolean>(false),

      registerFormName: ref<string>(''),
      registerFormNascimento: ref<string>(''),
      registerFormCPF: ref<string>(''),
      registerFormEmail: ref<string>(''),
      registerFormEmailSecundario: ref<string>(''),
      registerFormMatricula: ref<number>(),
      registerFormSenha: ref<string>(''),
      registerFormSenhaConfirm: ref<string>(''),
      registerFormTelefone: ref<string>(''),
      registerFormDepartamento: [
        'Tecnologia da Informação',
        'Recursos Humanos',
      ],

      departamentoSelect: ref<string>(),
      visiblePwd1: ref<boolean>(true),
      visiblePwd2: ref<boolean>(true),
    };
  },
  methods: {
    requiredField(val: string) {
      return !!val || 'Campo obrigatório';
    },
    NomeValid(val: string) {
      return val.length >= 12 || 'O nome deve possuir ao menos 12 caracteres';
    },
    matriculaValid(val: string) {
      return val.length === 12 || 'A matrícula deve possuir 12 caracteres';
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
      return val === this.registerFormSenha || 'As senhas devem ser igual.';
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

    checkForm() {
      const registerForms = [
        {
          form: this.$refs.registerForm1 as unknown as {
            validate: () => Promise<boolean>;
          },
          state: 'stateForm1',
        },
        {
          form: this.$refs.registerForm2 as unknown as {
            validate: () => Promise<boolean>;
          },
          state: 'stateForm2',
        },
        {
          form: this.$refs.registerForm3 as unknown as {
            validate: () => Promise<boolean>;
          },
          state: 'stateForm3',
        },
      ];
      const currentStep = this.registerStepperStep;
      const currentForm = registerForms[currentStep - 1];

      if (currentForm && currentForm.form) {
        currentForm.form.validate().then((success: boolean) => {
          if (currentForm.state == 'stateForm1') {
            this.registerStepperStateForm1 = !success;
          } else if (currentForm.state == 'stateForm2') {
            this.registerStepperStateForm2 = !success;
          } else {
            this.registerStepperStateForm3 = !success;
          }
        });
      }
    },
    nextStepper() {
      this.checkForm();
      if (this.registerStepperStep === 1) {
        this.registerStepperDone1 = true;
      } else if (this.registerStepperStep === 2) {
        this.registerStepperDone2 = true;
      } else {
        this.registerStepperDone3 = true;
        this.$props.method();
      }
      (this.$refs.stepper as QStepper).next();
    },
    backStepper() {
      if (this.registerStepperStep === 2) {
        this.registerStepperDone1 = false;
      } else if (this.registerStepperStep === 3) {
        this.registerStepperDone2 = false;
      }
      (this.$refs.stepper as QStepper).previous();
    },
  },
});
</script>
