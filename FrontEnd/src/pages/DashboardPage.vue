<template>
  <q-page class="column items-center justify-around">
    <div class="q-pt-md q-px-lg row justify-between full-width">
      <p class="text-weight-bold text-h6 text-secondary">Dashboard</p>
      <p class="text-weight-light text-subtitle1 text-secondary">
        {{ data }}
      </p>
    </div>

    <div class="full-width">
      <p
        class="text-weight-medium text-body1 text-secondary q-mb-sm text-center q-pb-sm"
      >
        Marcações de Hoje
      </p>
      <card-registros
        :registros="[registro1, registro2, registro3, registro4]"
        :cards-registro="cardsRegistro"
        :is-dense="isDense"
      ></card-registros>
    </div>

    <div class="row items-center justify-evenly q-my-md">
      <div class="bg-white calendar-card shadow-1 q-ma-md">
        <q-date
          v-model="dataAtual"
          readonly
          color="white"
          text-color="secondary"
          subtitle=""
          :title="mesCalendario + ' ' + anoCalendario"
          :events="[dataAtual]"
          event-color="secondary"
          class="no-shadow"
        />
      </div>

      <div
        class="clock-card row items-center justify-around q-mx-md q-pa-lg bg-white shadow-1"
      >
        <ClockComponent class="q-mr-md"></ClockComponent>

        <div class="column items-center">
          <div
            class="clock-card-content q-py-sm q-my-sm column items-center"
            :class="isDense ? 'q-px-sm' : 'q-px-lg'"
          >
            <p class="text-body1 text-secondary text-weight-light q-my-xs">
              Tempo trabalhando
            </p>
            <p class="text-h6 text-secondary text-weight-bold q-my-xs">
              {{ tempoTrabalhado }}
            </p>
          </div>

          <div
            class="clock-card-content q-py-sm q-my-sm column items-center"
            :class="isDense ? 'q-px-sm' : 'q-px-lg'"
          >
            <p class="text-body1 text-secondary text-weight-light q-my-xs">
              Tempo em intervalo
            </p>
            <p class="text-h6 text-secondary text-weight-bold q-my-xs">
              {{ tempoIntervalo }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </q-page>
</template>
<style>
.clock-card {
  min-height: 300px;
  max-height: 400px;
  border: 1px solid #d6e3f2;
  border-radius: 10px;
}

.clock-card-content {
  border: 1px solid #d6e3f2;
  border-radius: 10px;
}

.calendar-card {
  border: 1px solid #d6e3f2;
  border-radius: 10px;
}

.q-date {
  height: 300px;
}

.q-date__header-subtitle {
  font-size: 0;
  height: 0;
}

.q-date__header-title {
  font-weight: bold;
}
</style>
<script lang="ts">
import CardRegistros from 'src/components/CardRegistros.vue';
import ClockComponent from 'src/components/ClockComponent.vue';
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'DashboardPage',
  props: {
    isDense: Boolean,
    data: String,
  },
  components: {
    CardRegistros,
    ClockComponent,
  },
  setup() {
    const data = new Date();
    return {
      matriculaForm: ref<string>(''),
      registro1: ref<string>('08h00'),
      registro2: ref<string>('12h00'),
      registro3: ref<string>('13h00'),
      registro4: ref<string>('17h00'),
      tempoTrabalhado: ref<string>(''),
      tempoIntervalo: ref<string>(''),
      dataAtual: ref<string>(
        data.getFullYear().toString() +
          '/' +
          (data.getMonth() + 1).toString() +
          '/' +
          data.getDate().toString()
      ),
      mesCalendario: ref<string>(''),
      anoCalendario: ref<string>(''),
      cardsRegistro: [
        {
          title: 'Entrada',
          color: 'bg-light-blue-4',
          textColor: 'text-blue-10',
        },
        {
          title: 'Intervalo',
          color: 'bg-amber-4',
          textColor: 'text-deep-orange-10',
        },
        {
          title: 'Retorno',
          color: 'bg-indigo-4',
          textColor: 'text-deep-purple-10',
        },
        {
          title: 'Saída',
          color: 'bg-light-green-4',
          textColor: 'text-teal-10',
        },
      ],
    };
  },

  methods: {
    calculaDiferenca(start: string, end: string): number {
      const startHora = Number(start.split('h')[0]);
      const startMinuto = Number(start.split('h')[1]);
      const endHora = Number(end.split('h')[0]);
      const endMinuto = Number(end.split('h')[1]);

      const startDate = new Date(0, 0, 0, startHora, startMinuto);
      const endDate = new Date(0, 0, 0, endHora, endMinuto);

      return (endDate.getTime() - startDate.getTime()) / (1000 * 60);
    },
    updateTime() {
      const trabalhoManha = this.calculaDiferenca(
        this.registro1,
        this.registro2
      );
      const trabalhoTarde = this.calculaDiferenca(
        this.registro3,
        this.registro4
      );
      const intervalo = this.calculaDiferenca(this.registro2, this.registro3);

      this.tempoTrabalhado = `${Math.floor(
        (trabalhoManha + trabalhoTarde) / 60
      )}h ${(trabalhoManha + trabalhoTarde) % 60}min`;
      this.tempoIntervalo = `${Math.floor(intervalo / 60)}h ${
        intervalo % 60
      }min`;
    },
    updateDate() {
      const data = new Date();
      const mesFormat = data.toLocaleString('pt-BR', {
        timeZone: 'America/Manaus',
        month: 'long',
      });
      this.anoCalendario = data.toLocaleString('pt-BR', {
        timeZone: 'America/Manaus',
        year: 'numeric',
      });
      this.mesCalendario =
        mesFormat.charAt(0).toUpperCase() + mesFormat.slice(1);
    },
  },
  mounted() {
    this.updateTime();
    this.updateDate();
  },
});
</script>
