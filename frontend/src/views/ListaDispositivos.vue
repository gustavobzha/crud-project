<template>
  <div style="margin: 0 auto; width: 80%">
    <Toast />
    <Panel header="Dispositivos">
      <Menubar :model="items" />
      <br />
      <DataTable
        :value="dispositivos"
        :selection.sync="dispositivoSelecionado"
        selectionMode="single"
        dataKey="id"
        sortField="local"
        :sortOrder="1"
        :paginator="true"
        :rows="10"
      >
        <Column field="numeroSerie" header="Número Série"></Column>
        <Column field="modelo" header="Modelo"></Column>
        <Column field="status" header="Status"></Column>
      </DataTable>
    </Panel>
    <Dialog
      header="Adicionar Dispositivo"
      :visible.sync="displayModal"
      :modal="true"
    >
      <span class="p-float-label">
        <InputText
          id="CNPJ"
          type="text"
          v-model="dispositivo.cnpj"
          style="width: 100%"
        />
        <label for="cnpj">CNPJ</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Razão Social"
          type="text"
          v-model="dispositivo.razaoSocial"
          style="width: 100%"
        />
        <label for="razaoSocial">Razão Social</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Nome Fantasia"
          type="text"
          v-model="dispositivo.nomeFantasia"
          style="width: 100%"
        />
        <label for="nomeFantasia">Nome Fantasia</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Endereço"
          type="text"
          v-model="dispositivo.endereco"
          style="width: 100%"
        />
        <label for="endereco">Endereço</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Telefone"
          type="text"
          v-model="dispositivo.telefone"
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
import DispositivoService from "../service/DispositivoService";

export default {
  name: "CrudApp",
  data() {
    return {
      dispositivos: null,
      dispositivo: {
        numeroSerie: null,
        modelo: null,
        status: null,
      },
      dispositivoSelecionado: {
        numeroSerie: null,
        modelo: null,
        status: null,
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
  dispositivoService: null,
  created() {
    this.dispositivoService = new DispositivoService();
  },
  mounted() {
    this.getAll();
  },
  methods: {
    showSaveModal() {
      this.displayModal = true;
    },
    showEditModal() {
      this.dispositivo = this.dispositivoSelecionado;
      this.displayModal = true;
    },
    getAll() {
      this.dispositivoService.getAll().then((data) => {
        this.dispositivos = data.data;
      });
    },
    save() {
      this.dispositivoService.save(this.dispositivo).then((data) => {
        console.log(data);
        if (data.status === 200) {
          this.displayModal = false;
          this.dispositivo = {
            numeroSerie: null,
            modelo: null,
            status: null,
          };
          this.$toast.add({
            severity: "success",
            summary: "Concluído",
            detail: "Dispositivo adicionado com sucesso!",
            life: 3000,
          });
        }
      });
    },
    delete() {
      if (confirm("Tem certeza que deseja excluir este dispositivo?")) {
        this.dispositivoService
          .delete(this.dispositivoSelecionado.id)
          .then((data) => {
            if (data.status === 200) {
              this.$toast.add({
                severity: "success",
                summary: "Remoção concluída",
                detail: "Dispositivo excluído com sucesso",
                life: 3000,
              });
            }
          });
      }
    },
    closeModal() {
      this.displayModal = false;
      this.dispositivo = {
        numeroSerie: null,
        modelo: null,
        status: null,
      };
    },
  },
};
</script>

<style>
</style>