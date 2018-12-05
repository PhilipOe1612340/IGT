<template>
  <div>
    <v-card elevation-6 v-if="flight">
      <br>
      <span style="color:gray;fload:right;">id: {{flight.flightId}}</span>
      <br>
      <v-layout row wrap>
        <v-flex v-for="(airport, index) in airports" :key="airport.airportId">
          <v-icon v-if="index > 0 && index < airports.length" class="arrow">keyboard_arrow_right</v-icon>
          <v-icon v-if="index == 0" class="arrow">flight_takeoff</v-icon>
          {{airport.name}} ({{airport.short}})
          <v-icon v-if="index == airports.length - 1" class="arrow">flight_land</v-icon>
        </v-flex>
      </v-layout>
      <br>
      <v-layout row break id="info">
        <v-flex xs12 sm6 md4>
          <b>Start:</b>
          {{flight.time}}
        </v-flex>
        <v-flex xs12 sm6 md4>
          <b>Duration:</b>
          {{duration}}
        </v-flex>
        <v-flex xs12 sm6 md4>
          <b>Arrival:</b>
          {{arrival}}
        </v-flex>
      </v-layout>
      <v-layout row>
        <v-flex xs12>
          <b>Miles:</b>
          {{flight.miles}}
        </v-flex>
      </v-layout>
      <br>
      <v-btn flat color="orange" @click="$emit('selected', flight)">{{text || 'Select'}}</v-btn>
      <br>
    </v-card>
  </div>
</template>

<script>
import moment from "moment";

export default {
  props: {
    flight: Object,
    text: String
  },
  mounted() {
    moment.locale('de');
  },
  computed: {
    airports() {
      const all = this.$store.getters.airports;
      return this.flight.airportsList.map(id =>
        all.find(airport => airport.airportId === id)
      );
    },
    arrival() {
      return moment().hour(this.flight.time.slice(0, 2)).minutes(this.flight.time.slice(3)).add(this.flight.duration, "m").format('LT');
    },
    duration() {
      const duration = moment.duration(this.flight.duration, "minutes")
      return duration.hours() + "h, " + duration.minutes() + "m"

    }
  }
};
</script>

<style lang="scss" scoped>
.arrow {
  margin-right: 15px;
}
#info {
  color: #000000;
  background: #adadad38;
  padding: 8px 0px;
  border-radius: 8px;
  margin: 5px;
}
</style>
