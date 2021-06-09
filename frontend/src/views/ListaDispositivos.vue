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
        dataKey="numeroSerie"
        sortField="numeroSerie"
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
      <br />
      <span class="p-float-label">
        <InputText
          id="Número Série"
          type="text"
          v-model="dispositivo.numeroSerie"
          style="width: 100%"
        />
        <label for="numeroSerie">Número Série</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="Modelo"
          type="text"
          v-model="dispositivo.modelo"
          style="width: 100%"
        />
        <label for="modelo">Modelo</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          type="text"
          v-model="dispositivo.status"
          style="width: 100%"
          disabled
        />
        <label for="status">Status</label>
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
      label="Pagina Inicial"
      icon="pi pi-arrow-left"
      @click="$router.push('home')"
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
      this.dispositivo.status = "DESATIVADO";
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
          .delete(this.dispositivoSelecionado.numeroSerie)
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