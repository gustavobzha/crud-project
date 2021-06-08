<template>
  <div style="margin: 0 auto; width: 80%">
    <Toast />
    <Panel header="Linhas">
      <Menubar :model="items" />
      <br />
      <DataTable
        :value="linhas"
        :selection.sync="linhaSelecionada"
        selectionMode="single"
        dataKey="id"
        sortField="cidade"
        :sortOrder="1"
        :paginator="true"
        :rows="10"
      >
        <Column field="id" header="ID"></Column>
        <Column field="cidade" header="Cidade"></Column>
      </DataTable>
    </Panel>
    <Dialog header="Adicionar Linha" :visible.sync="displayModal" :modal="true">
      <span class="p-float-label">
        <InputText
          id="CNPJ"
          type="text"
          v-model="linha.cnpj"
          style="width: 100%"
        />
        <label for="cnpj">CNPJ</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Razão Social"
          type="text"
          v-model="linha.razaoSocial"
          style="width: 100%"
        />
        <label for="razaoSocial">Razão Social</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Nome Fantasia"
          type="text"
          v-model="linha.nomeFantasia"
          style="width: 100%"
        />
        <label for="nomeFantasia">Nome Fantasia</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Endereço"
          type="text"
          v-model="linha.endereco"
          style="width: 100%"
        />
        <label for="endereco">Endereço</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Telefone"
          type="text"
          v-model="linha.telefone"
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
      label="Voltar em Clientes"
      icon="pi pi-arrow-left"
      @click="$router.push('listaClientes')"
      class="p-button-text"
    />
  </div>
</template>

<script>
import LinhaService from "../service/LinhaService";
import router from "../router"

export default {
  name: "CrudApp",
  data() {
    return {
      linhas: null,
      linha: {
        id: null,
        cidade: null,
        estruturas: null,
      },
      
      linhaselecionada: {
        id: null,
        cidade: null,
        estruturas: null,
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
        {
            label: "Estruturas",
            icon: "pi pi-fw pi-ticket",
            command: () => {
                this.showEstruturas();
            }
        }
      ],
      displayModal: false,
    };
  },
  linhaService: null,
  created() {
    this.linhaService = new LinhaService();
  },
  mounted() {
    this.getAll();
  },
  methods: {
    showSaveModal() {
      this.displayModal = true;
    },
    showEditModal() {
      this.linha = this.linhaSelecionada;
      this.displayModal = true;
    },
    getAll() {
      this.linhaService.getAll().then((data) => {
        this.linhas = data.data;
      });
    },
    save() {
      this.linhaService.save(this.linha).then((data) => {
        console.log(data);
        if (data.status === 200) {
          this.displayModal = false;
          this.linha = {
            id: null,
        cidade: null,
        estruturas: null,
          };
          this.$toast.add({
              severity: "success",
              summary: "Concluído",
              detail: "Linha adicionado com sucesso!",
              life: 3000,
            });
        }
      });
    },
    delete() {
      if (confirm("Tem certeza que deseja excluir este linha?")) {
        this.linhaService.delete(this.linhaSelecionada.id).then((data) => {
          if (data.status === 200) {
            this.$toast.add({
              severity: "success",
              summary: "Remoção concluída",
              detail: "Linha excluído com sucesso",
              life: 3000,
            });
          }
        });
      }
    },
    closeModal() {
      this.displayModal = false;
      this.linha = {
        id: null,
        cidade: null,
        estruturas: null,
      };
    },
    showEstruturas(){
        router.push('listaEstruturas')
    },
  },
};
</script>

<style>
</style>