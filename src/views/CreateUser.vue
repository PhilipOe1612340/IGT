<template>
  <div>
    <v-speed-dial bottom right>
      <v-btn slot="activator" @click="dialog = true" dark fab>
        <v-icon>account_circle</v-icon>
      </v-btn>
    </v-speed-dial>
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-text>
          <v-text-field v-model="name" label="Name"></v-text-field>
          <v-text-field v-model="address" label="Adress"></v-text-field>
          <v-combobox v-model="phones" label="Phones" chips clearable solo multiple>
            <template slot="selection" slot-scope="data">
              <v-chip :selected="data.selected" close @input="remove(data.item)">
                <strong>{{ data.item }}</strong>&nbsp;
              </v-chip>
            </template>
          </v-combobox>
          <v-text-field v-model="flownMiles" label="Miles"></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn flat color="primary" @click="create()">Create</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    name: "",
    address: "",
    phones: [],
    flownMiles: 0
  }),
  methods: {
    create() {
      this.$store.dispatch("createCustomer", {
        name: this.name,
        address: this.address,
        phones: this.phones,
        flownMiles: this.flownMiles
      });
      this.clear();
    },
    clear() {
      this.name = "";
      this.address = "";
      this.phones = [];
      this.flownMiles = 0;
      this.dialog = false;
    },
    remove(item) {
      this.phones.splice(this.chips.indexOf(item), 1);
      this.phones = [...this.chips];
    }
  }
};
</script>
