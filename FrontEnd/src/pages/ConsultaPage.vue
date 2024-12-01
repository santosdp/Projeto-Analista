<template>
  <q-page class="column items-center justify-start">
    <div class="q-pt-md q-px-lg row justify-start full-width">
      <p class="text-weight-bold text-h6 text-secondary">
        Consulta de Registro de Ponto
      </p>
    </div>

    <div class="row justify-center items-center full-width">
      <generic-input
        label="Matricula"
        :model-value="matriculaUsuario"
        input-type="number"
        :is-outlined="true"
        :is-dense="true"
        :rules="[requiredField, matriculaValid]"
        class="input-perfil q-mx-sm"
      ></generic-input>
      <div class="row items-center justify-center">
        <generic-input
          label="Data Inicial"
          :model-value="data1Usuario"
          input-type="date"
          hint="DD/MM/YYYY"
          :is-outlined="true"
          mask="##/##/####"
          :is-dense="true"
          :rules="[]"
          class="input-consulta q-mx-sm"
        ></generic-input>

        <generic-input
          label="Data Final"
          :model-value="data2Usuario"
          input-type="date"
          hint="DD/MM/YYYY"
          :is-outlined="true"
          mask="##/##/####"
          :is-dense="true"
          :rules="[]"
          class="input-consulta q-mx-sm"
        ></generic-input>
      </div>
      <q-btn
        label="Pesquisar"
        type="submit"
        class="text-weight-bold q-mx-sm"
        color="secondary"
      ></q-btn>
    </div>

    <hr class="separator" />

    <lista-consulta
      :is-dense="isDense"
      :linhas="linhasRegistro"
    ></lista-consulta>
  </q-page>
</template>

<style scoped>
.separator {
  border-bottom: 1px solid #798aa3;
  width: calc(100% - 100px);
}
.input-consulta {
  width: 150px;
}

@media screen and (max-width: 600px) {
}
</style>
<script lang="ts">
import GenericInput from 'src/components/GenericInput.vue';
import ListaConsulta from 'src/components/ListaConsulta.vue';
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'ConsultarPage',

  props: {
    isDense: Boolean,
  },

  components: {
    GenericInput,
    ListaConsulta,
  },

  setup() {
    return {
      data1Usuario: ref<string>(''),
      data2Usuario: ref<string>(''),
      matriculaUsuario: ref<string>(''),
      matriculaFuncionario: ref<string>(''),
      cargoUsuario: ref<string>('Administrador'),
      linhasRegistro: ref([
        {
          date: '01/03/2023',
          entrada: '08:01',
          intervalo: '12:02',
          retorno: '13:02',
          saida: '17:00',
          status: 'Regular',
          porcentagem: 100,
        },
        {
          date: '02/03/2023',
          entrada: '08:05',
          intervalo: '12:00',
          retorno: '13:01',
          saida: '17:10',
          status: 'Regular',
          porcentagem: 100,
        },
        {
          date: '03/03/2023',
          entrada: '08:01',
          intervalo: '12:02',
          retorno: '13:02',
          saida: '17:00',
          status: 'Regular',
          porcentagem: 92,
        },
        {
          date: '03/05/2023',
          entrada: '08:00',
          intervalo: '12:02',
          retorno: '13:07',
          saida: '17:00',
          status: 'Regular',
          porcentagem: 98,
        },
        {
          date: '03/06/2023',
          entrada: '08:00',
          intervalo: '12:02',
          retorno: '13:07',
          saida: '17:00',
          status: 'Regular',
          porcentagem: 98,
        },
        {
          date: '03/07/2023',
          entrada: '08:00',
          intervalo: '12:02',
          retorno: '13:07',
          saida: '17:00',
          status: 'Regular',
          porcentagem: 98,
        },
        {
          date: '03/08/2023',
          entrada: '08:00',
          intervalo: '12:02',
          retorno: '13:07',
          saida: '17:00',
          status: 'Regular',
          porcentagem: 98,
        },
      ]),
    };
  },
  methods: {
    onSubmit() {},
    requiredField(val: string) {
      return !!val || 'Campo obrigatório';
    },
    matriculaValid(val: string) {
      return val.length === 12 || 'A matrícula deve possuir 12 caracteres';
    },
  },
});
</script>
