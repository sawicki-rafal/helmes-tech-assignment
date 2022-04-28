<template>
  <div class="full-page-wrapper">


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
      }
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
      console.log(this.form.sectors);
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
    setResponse(returnedSectorEntry){

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

.label{
  margin-bottom: 10px;
}
</style>