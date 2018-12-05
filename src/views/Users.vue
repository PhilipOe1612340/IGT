<template>
  <div>
    <v-layout>
      <v-flex xs12 sm6 offset-sm3 id="list">
        <v-card class="user" v-for="user in $store.getters.getAllUsers" :key="user.customerId">
          <v-card-title primary-title>
            <h3 class="headline mb-0">{{user.name}}</h3>
          </v-card-title>
          <table>
            <tr class="field" v-for="(field, index) in getFields(user)" :key="index">
              <td>
                <b>{{field}}:</b>
              </td>
              <td>{{user[field]}}</td>
            </tr>
          </table>
          <v-card-actions>
            <user-edit-dialog :userId="user.customerId"/>
            <v-btn flat @click="$store.dispatch('deleteCustomer', user.customerId)">delete</v-btn>
            <v-btn flat color="orange" @click="$store.dispatch('setCustomer', user.customerId)">use</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
    <create-user id="fab"/>
  </div>
</template>

<script>
import UserEditDialog from "./UserEditDialog.vue";
import CreateUser from "./CreateUser.vue";

export default {
  components: {
    UserEditDialog,
    CreateUser
  },
  methods: {
    getFields(user) {
      return Object.keys(user);
    }
  }
};
</script>

<style lang="scss" scoped>
.user {
  margin: 10px;
}
#list {
  margin-bottom: 80px;
}
.field {
  text-align: left;
  padding: 4px;
  outline: 1px solid #8080803d;
  text-transform: capitalize;
}
table {
  margin-left: 20px;
  width: calc(100% - 40px);
}
#fab {
  position: fixed;
  right: 20px;
  bottom: 100px;
  z-index: 500;
}
</style>
