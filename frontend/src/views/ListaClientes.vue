<template>
  <div style="margin: 0 auto; width: 80%">
    <Toast />
    <Panel header="Clientes">
      <Menubar :model="items" />
      <br />
      <DataTable
        :value="clientes"
        :selection.sync="clienteSelecionado"
        selectionMode="single"
        dataKey="id"
        sortField="razaoSocial"
        :sortOrder="1"
        :paginator="true"
        :rows="10"
      >
        <Column field="id" header="ID"></Column>
        <Column field="cnpj" header="CNPJ"></Column>
        <Column field="razaoSocial" header="Razão Social"></Column>
        <Column field="nomeFantasia" header="Nome Fantasia"></Column>
        <Column field="endereco" header="Endereço"></Column>
        <Column field="telefone" header="Telefone"></Column>
      </DataTable>
    </Panel>
    <Dialog
      header="Adicionar Cliente"
      :visible.sync="displayModal"
      :modal="true"
    >
      <span class="p-float-label">
        <InputText
          id="CNPJ"
          type="text"
          v-model="cliente.cnpj"
          style="width: 100%"
        />
        <label for="cnpj">CNPJ</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Razão Social"
          type="text"
          v-model="cliente.razaoSocial"
          style="width: 100%"
        />
        <label for="razaoSocial">Razão Social</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Nome Fantasia"
          type="text"
          v-model="cliente.nomeFantasia"
          style="width: 100%"
        />
        <label for="nomeFantasia">Nome Fantasia</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Endereço"
          type="text"
          v-model="cliente.endereco"
          style="width: 100%"
        />
        <label for="endereco">Endereço</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Telefone"
          type="text"
          v-model="cliente.telefone"
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
      label="Voltar a Página Inicial"
      icon="pi pi-arrow-left"
      @click="$router.push('home')"
      class="p-button-text"
    />
  </div>
</template>

<script>
import ClienteService from "../service/ClienteService";
import router from "../router";

export default {
  name: "ListaClientes",
  data() {
    return {
      clientes: null,
      cliente: {
        id: null,
        cnpj: null,
        razaoSocial: null,
        nomeFantasia: null,
        endereco: null,
        telefone: null,
        linhas: null,
      },
      clienteSelecionado: {
        id: null,
        cnpj: null,
        razaoSocial: null,
        nomeFantasia: null,
        endereco: null,
        telefone: null,
        linhas: null,
      },
      items: [
        {
          label: "Adicionar",
          icon: "pi pi-fw pi-user-plus",
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
          label: "Linhas do cliente",
          icon: "pi pi-fw pi-share-alt",
          command: () => {
            this.showLines();
          },
        },
      ],
      displayModal: false,
    };
  },
  clienteService: null,
  created() {
    this.clienteService = new ClienteService();
  },
  mounted() {
    this.getAll();
  },
  methods: {
    showSaveModal() {
      this.displayModal = true;
    },
    showEditModal() {
      this.cliente = this.clienteSelecionado;
      this.displayModal = true;
    },
    getAll() {
      this.clienteService.getAll().then((data) => {
        this.clientes = data.data;
      });
    },
    save() {
      this.clienteService.save(this.cliente).then((data) => {
        console.log(data);
        if (data.status === 200) {
          this.displayModal = false;
          this.cliente = {
            id: null,
            cnpj: null,
            razaoSocial: null,
            nomeFantasia: null,
            endereco: null,
            telefone: null,
            linhas: null,
          };
          this.$toast.add({
            severity: "success",
            summary: "Concluído",
            detail: "Cliente adicionado com sucesso!",
            life: 3000,
          });
        }
      });
    },
    delete() {
      if (confirm("Tem certeza que deseja excluir este cliente?")) {
        this.clienteService.delete(this.clienteSelecionado.id).then((data) => {
          if (data.status === 200) {
            this.delete = true;
            this.$toast.add({
              severity: "success",
              summary: "Remoção concluída",
              detail: "Cliente excluído com sucesso",
              life: 3000,
            });
          }
        });
      }
    },
    closeModal() {
      this.displayModal = false;
      this.cliente = {
        id: null,
        cnpj: null,
        razaoSocial: null,
        nomeFantasia: null,
        endereco: null,
        telefone: null,
        linhas: null,
      };
    },
    showLines() {
      if (this.clienteSelecionado.id){
        console.log(this.clienteSelecionado)
        localStorage.setItem('clienteId', this.clienteSelecionado.id.toString())
        router.push("listaLinhas");
        }
    },
  },
};
</script>

<style>
</style>