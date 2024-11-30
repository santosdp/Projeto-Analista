<template>
  <q-field
    borderless
    stack-label
    :label="label"
    color="secondary"
    :dense="isDense"
    :error="modelError"
    class="q-my-none"
  >
    <template v-slot:control>
      <q-input
        :outlined="isOutlined"
        :aria-label="label"
        :type="inputType"
        v-model="localValue"
        :hint="hint"
        :dense="isDense"
        :mask="mask"
        :readonly="isReadonly"
        :rules="rules"
        class="full-width q-my-sm"
      >
        <template v-if="iconPrepend" v-slot:prepend>
          <q-icon :name="iconPrepend"></q-icon>
        </template>
        <template v-if="iconAppend" v-slot:append>
          <q-icon :name="iconAppend" @click="visibleIcon" id="icon-append" />
        </template>
      </q-input>
      <div v-if="modelError" class="text-negative">
        {{ modelError }}
      </div>
    </template>
  </q-field>
</template>

<style scoped>
.q-field__label {
  font-weight: 800;
  font-size: 18px;
}
.q-field__native {
  text-align: center;
}
</style>

<script lang="ts">
import { defineComponent, PropType, ref, watch } from 'vue';

export default defineComponent({
  name: 'GenericInput',
  props: {
    label: {
      type: String,
      required: true,
    },
    modelValue: {
      type: [String, Number] as PropType<string | number | undefined>,
      required: true,
    },
    inputType: {
      type: String as PropType<
        | 'text'
        | 'number'
        | 'email'
        | 'password'
        | 'tel'
        | 'url'
        | 'date'
        | 'datetime-local'
      >,
      default: 'text',
    },
    hint: String,
    mask: String,
    iconAppend: String,
    iconPrepend: String,
    isReadonly: Boolean,
    isOutlined: Boolean,
    visibleIcon: Function as PropType<() => void>,
    isDense: {
      type: Boolean,
      required: true,
    },
    rules: {
      type: Array as PropType<Array<(value: string) => boolean | string>>,
      required: true,
    },
  },

  setup(props, { emit }) {
    const modelError = ref<boolean>();
    const localValue = ref(props.modelValue);

    watch(
      () => props.modelValue,
      (newValue) => {
        localValue.value = newValue;
      }
    );

    watch(localValue, (newValue) => {
      emit('update:modelValue', newValue);
    });

    return {
      modelError,
      localValue,
    };
  },

  mounted() {
    if (this.visibleIcon) {
      document.getElementById('icon-append')?.classList.add('cursor-pointer');
    }
  },
});
</script>
