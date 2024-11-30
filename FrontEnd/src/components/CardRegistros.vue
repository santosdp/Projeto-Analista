<template>
  <div class="row justify-center">
    <template v-for="(cardItem, index) in cardsRegistro" :key="index">
      <q-card
        class="card-info q-mx-sm q-my-xs"
        :class="registros[index] ? cardItem.color : 'bg-grey-4'"
      >
        <q-card-section>
          <p
            class="text-grey-9 text-h6 text-weight-medium text-center q-my-none"
          >
            {{ cardItem.title }}
          </p>
        </q-card-section>

        <q-card-section>
          <p
            class="text-h4 text-weight-bold text-center"
            :class="registros[index] ? cardItem.textColor : 'text-grey-6'"
          >
            {{ registros[index] != '' ? registros[index] : '' }}
          </p>
        </q-card-section>
      </q-card>
    </template>
  </div>
</template>

<style scoped>
.card-info {
  width: 175px;
  height: 175px;
  border: 1px solid #798aa3;
  border-radius: 10px;
}

@media screen and (max-width: 600px) {
  .card-info {
    width: 115px;
    height: 115px;
  }
  .q-card__section {
    padding: 0;
    margin-top: 10px;
    margin-bottom: 10px;
  }
}
</style>
<script lang="ts">
import { defineComponent, PropType } from 'vue';

interface Card {
  title: string;
  color: string;
  textColor: string;
}

export default defineComponent({
  name: 'CardRegistros',
  props: {
    isDense: Boolean,
    registros: {
      type: Array as PropType<string[]>,
      required: true,
    },
    cardsRegistro: {
      type: Array as PropType<Card[]>,
      required: true,
    },
  },
  setup() {
    return {};
  },
});
</script>
