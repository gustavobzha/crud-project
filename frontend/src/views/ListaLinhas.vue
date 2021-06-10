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
      <br />
      <span class="p-float-label">
        <InputText
          id="Cidade"
          type="text"
          v-model="linha.cidade"
          style="width: 100%"
        />
        <label for="cidade">Cidade</label>
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
import ClienteService from "@/service/ClienteService"
import LinhaService from "../service/LinhaService";
import router from "../router"

export default {
  name: "ListaLinhas",
  data() {
    return {
      linhas: null,
      linha: {
        id: null,
        cidade: null,
        estruturas: null,
      },
      clienteId: null,
      cliente: {
        id: null,
        cnpj: null,
        razaoSocial: null,
        nomeFantasia: null,
        endereco: null,
        telefone: null,
        linhas: null,
      },
      linhaSelecionada: {
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
  clienteService: null,
  created() {
    this.linhaService = new LinhaService();
    this.clienteService = new ClienteService();
  },
  mounted() {
    this.clienteId = parseInt(localStorage.getItem('clienteId'))
    this.getAll();
    this.getCliente();
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
      this.linhaService.getLinhasCliente(this.clienteId).then((data) => {
        this.linhas = data.data;
      });
    },
    save() {
      this.linhaService.save(this.linha).then((data) => {
        if (data.status === 200) {
          this.linha = data.data;
          var dtoCL = this.makeDTO(this.clienteId,this.linha.id)
          this.clienteService.addLinha(dtoCL).then((data) => {
            if (data.status === 200) {
                this.displayModal = false;
                this.linha = {
                id: null,
                cidade: null,
                estruturas: null,
              };
              console.log(data.data)
              this.$toast.add({
                  severity: "success",
                  summary: "Concluído",
                  detail: "Linha adicionado com sucesso!",
                  life: 3000,
              })
            }
          })
        }
      })
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
        localStorage.setItem("linhaId", this.linhaSelecionada.id.toString())
        router.push('listaEstruturas')
    },
    getCliente(){
        this.clienteService.getById(this.clienteId).then((data) => {
          this.cliente = data.data;
        });
    },
    makeDTO(idCliente, idLinha){
      return {
        idCliente: idCliente,
        idLinha: idLinha
      }
    },
  },
};
</script>

<style>
</style>