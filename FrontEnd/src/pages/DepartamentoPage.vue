<template>
  <q-page class="column items-center justify-start">
    <div class="q-pt-md q-px-lg row justify-start full-width">
      <p class="text-weight-bold text-h6 text-secondary">Departamento</p>
    </div>

    <q-form @submit.prevent="onSubmit" class="column justify-start q-mb-lg">
      <div class="row justify-between">
        <p class="text-weight-medium text-body1 text-secondary">
          Informações do Departamento
        </p>
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
          :model-value="nomeDepartamento"
          input-type="text"
          :is-readonly="isReadonly"
          :is-dense="isDense"
          :rules="[requiredField, NomeValid]"
          class="input-departamento q-mx-sm"
        ></generic-input>

        <generic-input
          label="Matricula do Gerente"
          :model-value="matriculaGerenteDepartamento"
          input-type="number"
          :is-readonly="isReadonly"
          :is-dense="isDense"
          :rules="[requiredField, matriculaValid]"
          class="input-departamento q-mx-sm"
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

    <hr class="separator" />

    <div class="row justify-around">
      <q-form
        @submit.prevent="onSubmit"
        class="q-pa-lg q-my-md departamento-form column items-center"
      >
        <p class="text-weight-bold text-h6 text-secondary text-center">
          Criar Departamento
        </p>

        <p class="text-subtitle text-dark">
          Para criar um novo departamento é necessário definir um gerente.
        </p>

        <generic-input
          label="Nome"
          :model-value="nomeNovoDepartamento"
          input-type="text"
          :is-outlined="true"
          :is-dense="true"
          :rules="[requiredField, NomeValid]"
          class="input-departamento q-mx-sm"
        ></generic-input>

        <generic-input
          label="Matricula do Gerente"
          :model-value="matriculaGerenteNovoDepartamento"
          input-type="number"
          :is-outlined="true"
          :is-dense="true"
          :rules="[requiredField, matriculaValid]"
          class="input-departamento q-mx-sm"
        ></generic-input>

        <q-btn
          label="Criar"
          type="submit"
          class="full-width text-weight-bold"
          color="secondary"
        ></q-btn>
      </q-form>
    </div>
  </q-page>
</template>

<style scoped>
.departamento-form {
  width: 75%;
  max-width: 400px;
  border: 1px solid black;
  border-radius: 8px;
}
.separator {
  border-bottom: 0.5px solid #798aa3;
  width: calc(100% - 100px);
}
.q-form {
  width: 60vw;
}
.input-departamento {
  width: 250px;
}
@media screen and (max-width: 600px) {
  .q-form {
    width: 80vw;
  }
  .input-departamento {
    width: 175px;
  }
}
</style>
<script lang="ts">
//import GenericInput from 'src/components/GenericInput.vue';
import GenericInput from 'src/components/GenericInput.vue';
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'DepartamentoPage',

  props: {
    isDense: Boolean,
    data: String,
  },
  components: {
    GenericInput,
  },

  setup() {
    return {
      nomeDepartamento: ref<string>(''),
      matriculaGerenteDepartamento: ref<string>(''),
      nomeNovoDepartamento: ref<string>(''),
      matriculaGerenteNovoDepartamento: ref<string>(''),

      isReadonly: ref<boolean>(true),
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
    matriculaValid(val: string) {
      return val.length === 12 || 'A matrícula deve possuir 12 caracteres';
    },
  },
});
</script>
