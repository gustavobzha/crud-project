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
        <Column field="faseA.numeroSerie" header="Fase A"></Column>
        <Column field="faseB.numeroSerie" header="Fase B"></Column>
        <Column field="faseC.numeroSerie" header="Fase C"></Column>
      </DataTable>
    </Panel>
    <Dialog header="Adicionar Estrutura" :visible.sync="displayModal" :modal="true">
      <span class="p-float-label">
        <InputText
          id="CNPJ"
          type="text"
          v-model="estrutura.cnpj"
          style="width: 100%"
        />
        <label for="cnpj">CNPJ</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Razão Social"
          type="text"
          v-model="estrutura.razaoSocial"
          style="width: 100%"
        />
        <label for="razaoSocial">Razão Social</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Nome Fantasia"
          type="text"
          v-model="estrutura.nomeFantasia"
          style="width: 100%"
        />
        <label for="nomeFantasia">Nome Fantasia</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Endereço"
          type="text"
          v-model="estrutura.endereco"
          style="width: 100%"
        />
        <label for="endereco">Endereço</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Telefone"
          type="text"
          v-model="estrutura.telefone"
          style="width: 100%"
        />
        <label for="telefone">Telefone</label>
      </span>
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
  name: "CrudApp",
  data() {
    return {
      estruturas: null,
      estrutura: {
        id: null,
        local: null,
        faseA: null,
        faseB: null,
        faseC: null,
      },
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
      this.estruturaService.getAll().then((data) => {
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