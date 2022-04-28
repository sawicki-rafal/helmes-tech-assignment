<template>
  <div class="full-page-wrapper">
    <div class="full-page-wrapper" v-if="errors.length>0">
      <div class="message-box">
        <span>Please correct the following error(s):</span>
        <ul>
          <li v-for="error in errors" :key="error">{{ error }}</li>
        </ul>
        <div @click="clearErrors" class="close">
          <img src="../assets/close-pink.svg" alt="close">
        </div>
      </div>
    </div>

    <form @submit.prevent="saveOrUpdate" class="form">

      <label for="name">Name:</label>
      <input id="name" type="text" v-model="form.name"/>

      <label class="label" for="tree-select">Sectors:</label>
      <treeselect id="tree-select" :multiple="true" :options="possibleSectors" v-model="form.sectors"/>

      <input type="checkbox" id="agreed-to-terms-checkbox" v-model="form.agreedToTerms"/>
      <label for="agreed-to-terms-checkbox">Agree to terms</label>
      <button type="submit" value="Save">Save</button>
    </form>


  </div>
</template>

<script>
import sectorService from "@/helpers/sectorService";
import sectorEntryService from "@/helpers/sectorEntryService";

import Treeselect from 'vue3-treeselect'
import 'vue3-treeselect/dist/vue3-treeselect.css'

export default {
  name: "DashboardView",
  components: {Treeselect},
  data() {
    return {
      possibleSectors: [],
      form: {
        id: null,
        name: null,
        sectors: [],
        agreedToTerms: null,
      },
      errors: []
    }
  },
  mounted() {
    this.initPossibleSectors();
  },
  methods: {
    initPossibleSectors() {
      sectorService.getAllSectors()
          .then(response => {
            const data = response.data;
            console.log(data);
            this.possibleSectors = this.buildTree(data);
          })
          .catch(error => {
            this.$store.dispatch('exception/addError', error);
            console.log(error.response.status);
          });
    },
    buildTree(sectors) {
      return sectors.map(sector => {
        let mappedSector = {
          label: sector.name,
          id: sector.id
        };
        if (sector.childrenSectors) {
          mappedSector = {...mappedSector, children: this.buildTree(sector.childrenSectors)}
        }
        return mappedSector;
      })
    },
    saveOrUpdate() {
      if (!this.isFormValid()) {
        return;
      }

      const sectorEntry = {
        id: this.form.id,
        name: this.form.name,
        sectors: this.form.sectors,
        agreedToTerms: this.form.agreedToTerms
      }
      if (sectorEntry.id === null) {
        this.save(sectorEntry);
      } else {
        this.update(sectorEntry);
      }
    },
    save(sectorEntry) {
      sectorEntryService.saveSectorEntry(sectorEntry)
          .then(response => {
            const data = response.data;
            this.setResponse(data);
          })
          .catch(error => {
            this.$store.dispatch('exception/addError', error);
            console.log(error.response);
          });
    },
    setResponse(returnedSectorEntry) {

      this.form = {
        id: returnedSectorEntry.id,
        name: returnedSectorEntry.name,
        agreedToTerms: returnedSectorEntry.agreedToTerms,
        sectors: returnedSectorEntry.sectors
      }
    },
    update(sectorEntry) {
      sectorEntryService.updateSectorEntry(sectorEntry)
          .then(response => {
            const data = response.data;
            console.log(data);
            this.form = data;
          })
          .catch(error => {
            this.$store.dispatch('exception/addError', error);
            console.log(error.response);
          });
    },
    isFormValid() {
      const form = this.form;

      if (form.name?.length > 2 && form.sectors.length !== 0 && form.agreedToTerms) {
        return true;
      }

      this.errors = [];
      if (!(form.name?.length > 2)) {
        this.errors.push('Name must have at least 3 characters!');
      }
      if (form.sectors?.length === 0) {
        this.errors.push('Sectors cannot be empty!');
      }
      if (!form.agreedToTerms) {
        this.errors.push('You have to agree to terms!');
      }

      return false;
    },
    clearErrors() {
      this.errors = [];
    }
  }
}
</script>

<style lang="scss" scoped>
.full-page-wrapper {
  @extend %primary-backgroud, %full-page;
  @include centerChildren($vertical-padding, $horizontal-padding);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  gap: 10px;
}

.button {
  @extend %black-outline-button;
}

.label {
  margin-bottom: 10px;
}

.message-box {
  @extend %rounded;
  position: relative;
  width: 100%;
  padding: ($large-padding+$logo-size-small) $horizontal-padding;
  background: $error-color;
}

.close {
  position: absolute;
  top: $standard-padding;
  right: $large-padding;
  width: $logo-size-small;
  height: $logo-size-small;
}

</style>