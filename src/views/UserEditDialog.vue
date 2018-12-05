<template>
  <v-dialog v-model="dialog" max-width="600px">
    <v-btn slot="activator" flat>edit</v-btn>
    <v-card>
      <v-card-title>
        <span class="headline">Edit User</span>
      </v-card-title>
      <v-card-text>
        <v-container grid-list-md>
          <v-layout wrap>
            <v-flex xs12 v-for="(field, index) in getFields()" :key="index">
              <div v-if="isArray(field)">
                <span>{{field}}:</span>
                <div class="Array">
                  <v-text-field
                    v-for="(fields, index) in user[field]"
                    :key="index"
                    required
                    v-model="user[field][index]"
                    clearable
                    @change="add(field)"
                  ></v-text-field>
                  <v-btn @click="add(field)">+</v-btn>
                </div>
              </div>
              <v-text-field :label="field" v-else required v-model="user[field]"></v-text-field>
            </v-flex>
            <v-flex xs12>
              <v-select
                :items="items"
                v-model="status"
                item-text="status"
                item-value="miles"
                label="Class"
                required
              ></v-select>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" flat @click="cancel()">Close</v-btn>
        <v-btn color="blue darken-1" flat @click="save()">Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: {
    userId: Number
  },
  data: () => ({
    dialog: false,
    items: [
      { status: "Gold", miles: 10000 },
      { status: "Silver", miles: 5000 },
      { status: "Bronze", miles: 2500 },
      { status: "none", miles: 0 }
    ]
  }),
  methods: {
    getFields() {
      const excluded = ["customerId", "status"];
      const fields = Object.keys(this.user).filter(
        key => !excluded.includes(key)
      );
      return fields;
    },
    save() {
      this.$store.dispatch("updateCustomer", this.user);
      this.dialog = false;
    },
    cancel() {
      this.$store.dispatch("allCustomers");
      this.dialog = false;
    },
    isArray(field) {
      return Array.isArray(this.user[field]);
    },
    add(field) {
      this.user[field] = this.user[field].filter(
        entry => entry && entry !== ""
      );
      this.user[field].push("");
    }
  },
  computed: {
    user() {
      return this.$store.getters.getAllUsers.find(
        c => c.customerId === this.userId
      );
    },
    status: {
      get() {
        return this.items.find(
          item => item.miles >= Math.floor(this.user.flownMiles / 2500) * 2500
        );
      },
      set(val) {
        this.user.status = this.items.find(item => item.miles === val).status;
        this.user.flownMiles = val;
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.Array {
  margin: -5px 15px 0px 75px;
}
span {
  font-weight: bold;
  float: left;
  margin-top: -5px;
}
</style>
