<template>
  <q-page class="column items-center justify-start">
    <div class="q-pt-md q-px-lg row justify-start full-width">
      <p class="text-weight-bold text-h6 text-secondary">
        Justificar Ponto Errado
      </p>
    </div>

    <q-form
      @submit.prevent="onSubmit"
      class="q-pa-lg q-my-md justificar-form column items-center"
    >
      <p class="text-subtitle text-dark">
        Confirme sua matricula e defina o ponto marcado errado para justificar.
      </p>

      <generic-input
        label="Matricula"
        :model-value="matriculaForm"
        input-type="number"
        :is-outlined="true"
        :is-dense="true"
        :rules="[requiredField, matriculaValid]"
        class="input-justificar q-mx-sm"
      ></generic-input>

      <generic-input
        label="Data"
        :model-value="dataForm"
        input-type="date"
        :is-outlined="true"
        :is-dense="true"
        hint="DD/MM/YYYY"
        mask="##/##/####"
        :rules="[requiredField]"
        class="input-justificar q-mx-sm"
      ></generic-input>

      <q-field
        borderless
        stack-label
        label="Ponto que deseja justificar"
        :dense="true"
        class="q-my-none input-justificar"
      >
        <template v-slot:control>
          <q-select
            outlined
            v-model="pontoSelecionadoForm"
            :options="pontosForm"
            :rules="[requiredField]"
            class="full-width q-my-sm"
          />
        </template>
      </q-field>

      <q-field
        borderless
        stack-label
        label="Justificativa"
        :dense="true"
        class="q-my-none input-justificar"
      >
        <template v-slot:control>
          <q-select
            outlined
            v-model="justificativaSelecionadoForm"
            :options="justificativasForm"
            :rules="[requiredField]"
            class="full-width q-my-sm"
          />
        </template>
      </q-field>

      <q-btn
        label="Confirmar"
        type="submit"
        class="full-width text-weight-bold"
        color="secondary"
      ></q-btn>
    </q-form>
  </q-page>
</template>
<style scoped>
.justificar-form {
  width: 75%;
  max-width: 400px;
  border: 1px solid black;
  border-radius: 8px;
}
.input-justificar {
  width: 250px;
}
@media screen and (max-width: 600px) {
  .input-departamento {
    width: 175px;
  }
}
</style>
<script lang="ts">
import GenericInput from 'src/components/GenericInput.vue';
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'JustificarPage',

  props: {
    isDense: Boolean,
    data: String,
  },
  components: {
    GenericInput,
  },
  setup() {
    return {
      matriculaForm: ref<string>(''),
      dataForm: ref<string>(''),
      justificativaSelecionadoForm: ref<string>(''),
      pontoSelecionadoForm: ref<string>(''),
      justificativasForm: [
        'Atestado Médico',
        'Motivo de Saúde',
        'Esqueceu',
        'Engano',
      ],
      pontosForm: ['Entrada', 'Intervalo', 'Retorno', 'Saída', 'Dia inteiro'],
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
