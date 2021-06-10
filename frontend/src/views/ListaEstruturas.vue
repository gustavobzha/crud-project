<template>
  <div style="margin: 0 auto; width: 80%">
    <Toast />
    <Panel header="Estruturas">
      <Menubar :model="items" />
      <br />
      <DataTable
        :value="estruturas"
        :selection.sync="estruturaSelecionada"
        selectionMode="single"
        dataKey="id"
        sortField="local"
        :sortOrder="1"
        :paginator="true"
        :rows="10"
      >
        <Column field="id" header="ID"></Column>
        <Column field="local" header="Local"></Column>
        <Column field="faseA.modelo" header="Fase A"></Column>
        <Column field="faseB.modelo" header="Fase B"></Column>
        <Column field="faseC.modelo" header="Fase C"></Column>
      </DataTable>
    </Panel>
    <Dialog header="Adicionar Estrutura" :visible.sync="displayModal" :modal="true">
      <span class="p-float-label">
        <InputText
          id="Local"
          type="text"
          v-model="estrutura.local"
          style="width: 100%"
        />
        <label for="local">Local</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Fase A"
          type="text"
          v-model="estrutura.faseA"
          style="width: 100%"
        />
        <Dropdown v-model="selectedCity1" :options="cities" optionLabel="name" placeholder="Selecione o dispositivo" />
        <label for="faseA">Fase A</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Fase B"
          type="text"
          v-model="estrutura.faseB"
          style="width: 100%"
        />
        <label for="faseB">Fase B</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Fase C"
          type="text"
          v-model="estrutura.faseC"
          style="width: 100%"
        />
        <label for="faseC">Fase C</label>
      </span>
      <br />
      
      <br />
      <template #footer>
        <Button
          label="Salvar"
          icon="pi pi-check"
          @click="save"
          class="p-button-text"
          autofocus
        />
        <Button
          label="Cancelar"
          icon="pi pi-times"
          @click="closeModal"
          class="p-button-text"
        />
      </template>
    </Dialog>
    <Button
      label="Voltar em Linhas"
      icon="pi pi-arrow-left"
      @click="$router.push('listaLinhas')"
      class="p-button-text"
    />
  </div>
</template>

<script>
import EstruturaService from "../service/EstruturaService";

export default {
  name: "ListaEstruturas",
  data() {
    return {
      linhaId: null,
      estruturas: null,
      estrutura: {
        id: null,
        local: null,
        faseA: null,
        faseB: null,
        faseC: null,
      },
      dispositivoSelecionado1: null,
      dispositivoSelecionado2: null,
      dispositivoSelecionado3: null,
      dispositivos: null,
      estruturaSelecionada: {
        id: null,
        local: null,
        faseA: null,
        faseB: null,
        faseC: null,
      },
      items: [
        {
          label: "Adicionar",
          icon: "pi pi-fw pi-plus",
          command: () => {
            this.showSaveModal();
          },
        },
        {
          label: "Editar",
          icon: "pi pi-fw pi-pencil",
          command: () => {
            this.showEditModal();
          },
        },
        {
          label: "Excluir",
          icon: "pi pi-fw pi-trash",
          command: () => {
            this.delete();
          },
        },
        {
          label: "Atualizar",
          icon: "pi pi-fw pi-refresh",
          command: () => {
            this.getAll();
          },
        },
      ],
      displayModal: false,
    };
  },
  estruturaService: null,
  created() {
    this.estruturaService = new EstruturaService();
  },
  mounted() {
    this.linhaId = parseInt(localStorage.getItem('clienteId'))
    this.getAll();
  },
  methods: {
    showSaveModal() {
      this.displayModal = true;
    },
    showEditModal() {
      this.estrutura = this.estruturaSelecionada;
      this.displayModal = true;
    },
    getAll() {
      this.estruturaService.getEstruturasLinha(parseInt(localStorage.getItem("linhaId"))).then((data) => {
        this.estruturas = data.data;
      });
    },
    save() {
      this.estruturaService.save(this.estrutura).then((data) => {
        console.log(data);
        if (data.status === 200) {
          this.displayModal = false;
          this.estrutura = {
            id: null,
            local: null,
            faseA: null,
            faseB: null,
            faseC: null,
          };
          this.$toast.add({
            severity: "success",
            summary: "Concluído",
            detail: "Estrutura adicionado com sucesso!",
            life: 3000,
          });
        }
      });
    },
    delete() {
      if (confirm("Tem certeza que deseja excluir este estrutura?")) {
        this.estruturaService
          .delete(this.estruturaSelecionada.id)
          .then((data) => {
            if (data.status === 200) {
              this.$toast.add({
                severity: "success",
                summary: "Remoção concluída",
                detail: "Estrutura excluído com sucesso",
                life: 3000,
              });
            }
          });
      }
    },
    closeModal() {
      this.displayModal = false;
      this.estrutura = {
        id: null,
        local: null,
        faseA: null,
        faseB: null,
        faseC: null,
      };
    },
  },
};
</script>

<style>
</style>