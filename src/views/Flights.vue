<template>
  <div>
    <v-card>
      <v-layout row wrap>
        <v-flex xs12 sm6>
          <v-autocomplete
            v-model="startAirport"
            :items="$store.getters.airports"
            item-text="name"
            label="Select your origin"
            no-data-text="Airport not found"
            persistent-hint
            prepend-icon="mdi-city"
          />
        </v-flex>
        <v-flex xs12 sm6>
          <v-autocomplete
            v-model="goalAirport"
            :items="$store.getters.airports"
            item-text="name"
            label="Select your goal"
            no-data-text="Airport not found"
            persistent-hint
            prepend-icon="mdi-city"
          />
        </v-flex>
      </v-layout>
    </v-card>
    <v-layout justify-center v-if="startAirport && goalAirport">
      <v-flex xs10 mt-5>
        <flight-select :start-airport="startAirport" :goal-airport="goalAirport"/>
      </v-flex>
    </v-layout>

    <create-flight id="createFlightDial"/>
  </div>
</template>

<script>
import CreateFlight from "./CreateFlight.vue";
import FlightSelect from "./FlightSelect.vue";

export default {
  name: "Flights",
  components: {
    CreateFlight,
    FlightSelect
  },
  data: () => ({
    startAirport: "",
    goalAirport: ""
  }),
  methods: {}
};
</script>

<style lang="scss" scoped>
.v-menu {
  display: block;
  margin: 5px 12% !important;
}
#createFlightDial {
  position: fixed;
  right: 20px;
  bottom: 100px;
  z-index: 500;
}
</style>
