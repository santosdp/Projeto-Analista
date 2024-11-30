<template>
  <div class="column items-center justify-start q-my-md">
    <div class="legenda-card row">
      <p class="legenda1">Dia</p>
      <p class="legenda2">Entrada</p>
      <p class="legenda3">Intervalo</p>
      <p class="legenda4">Retorno</p>
      <p class="legenda5">Saída</p>
      <p class="legenda6 absolute-right">Presença</p>
    </div>
    <template v-for="(linha, index) in paginatedRows" :key="index">
      <q-card flat bordered class="q-mb-md">
        <q-card-section horizontal class="row items-center space-evenly">
          <q-card-section avatar>
            <q-icon
              name="circle"
              :color="defineCor(linha.porcentagem)"
              size="xs"
            />
          </q-card-section>

          <q-card-section>
            <p class="text-weight-light">
              {{ linha.date }}
            </p>
          </q-card-section>

          <q-card-section>
            <p class="text-weight-light">
              {{ linha.entrada }}
            </p>
          </q-card-section>

          <q-card-section>
            <p class="text-weight-light">
              {{ linha.intervalo }}
            </p>
          </q-card-section>

          <q-card-section>
            <p class="text-weight-light">
              {{ linha.retorno }}
            </p>
          </q-card-section>

          <q-card-section>
            <p class="text-weight-light">
              {{ linha.saida }}
            </p>
          </q-card-section>

          <q-card-section>
            <p class="text-weight-light">{{ linha.porcentagem }}%</p>
          </q-card-section>
        </q-card-section>
      </q-card>
    </template>
  </div>
  <q-pagination
    v-model="paginacao.page"
    :max="Math.ceil(linhas.length / paginacao.linhasPorPagina)"
    color="primary"
    input
    boundary-numbers
    class="q-mt-md"
  />
</template>
<style scoped>
.legenda-card {
  width: 100%;
  font-size: 14px;
}
.legenda1 {
  position: relative;
  left: 100px;
}
.legenda2 {
  position: relative;
  left: 190px;
}
.legenda3 {
  position: relative;
  left: 230px;
}
.legenda4 {
  position: relative;
  left: 270px;
}
.legenda5 {
  position: relative;
  left: 310px;
}
.legenda6 {
  position: relative;
  left: 360px;
}

.q-card__section p {
  margin: 0 10px;
  font-size: 16px;
}

@media screen and (max-width: 680px) {
  .q-card__section {
    padding: 8px;
  }
  .legenda1 {
    left: 90px;
  }
  .legenda2 {
    left: 155px;
  }
  .legenda3 {
    left: 180px;
  }
  .legenda4 {
    left: 200px;
  }
  .legenda5 {
    left: 230px;
  }
  .legenda6 {
    left: 260px;
  }
}

@media screen and (max-width: 570px) {
  .q-card__section {
    padding: 5px;
  }
  .q-card__section p {
    margin: 0 0;
    font-size: 14px;
  }

  .legenda-card {
    font-size: 10px;
  }
  .legenda1 {
    left: 60px;
  }
  .legenda2 {
    left: 110px;
  }
  .legenda3 {
    left: 120px;
  }
  .legenda4 {
    left: 125px;
  }
  .legenda5 {
    left: 135px;
  }
  .legenda6 {
    left: 145px;
  }
}

@media screen and (max-width: 450px) {
  .q-card__section {
    padding: 5px;
  }
  .q-card__section p {
    font-size: 12px;
  }

  .legenda1 {
    left: 60px;
  }
  .legenda2 {
    left: 95px;
  }
  .legenda3 {
    left: 100px;
  }
  .legenda4 {
    left: 105px;
  }
  .legenda5 {
    left: 110px;
  }
  .legenda6 {
    left: 115px;
  }
}

@media screen and (max-width: 320px) {
  .q-card__section p {
    font-size: 10px;
  }

  .q-card__section p {
    font-size: 8px;
  }

  .legenda1,
  .legenda2,
  .legenda3,
  .legenda4,
  .legenda5,
  .legenda6 {
    position: static;
    margin: 0 5px;
  }
}
</style>
<script lang="ts">
import { computed, defineComponent, PropType, ref } from 'vue';

interface Registros {
  date: string;
  entrada: string;
  intervalo: string;
  retorno: string;
  saida: string;
  porcentagem: number;
}

export default defineComponent({
  name: 'ListRegistros',

  props: {
    isDense: {
      type: Boolean,
      required: true,
    },
    linhas: {
      type: Array as PropType<Registros[]>,
      required: true,
    },
  },
  setup(props) {
    const paginacao = ref({
      page: 1,
      linhasPorPagina: 5,
    });

    const paginatedRows = computed(() => {
      const start =
        (paginacao.value.page - 1) * paginacao.value.linhasPorPagina;
      const end = start + paginacao.value.linhasPorPagina;
      return props.linhas.slice(start, end);
    });

    return {
      paginacao,
      paginatedRows,
    };
  },

  methods: {
    defineCor(porcentagem: number): string {
      if (porcentagem >= 95) {
        return 'green';
      } else if (porcentagem >= 80) {
        return 'orange';
      } else {
        return 'red';
      }
    },
  },
});
</script>
