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
    <Dialog
      header="Adicionar Estrutura"
      :visible.sync="displayModal"
      :modal="true"
    >
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
      <Dropdown
        v-model="dispositivoSelecionado1"
        :options="dispositivos"
        optionLabel="numeroSerie"
        placeholder="Selecione o dispositivo da Fase A"
        style="width: 100%"
      />
      <br />
      <br />
      <Dropdown
        v-model="dispositivoSelecionado2"
        :options="dispositivos"
        optionLabel="numeroSerie"
        placeholder="Selecione o dispositivo da Fase B"
        style="width: 100%"
      />
      <br />
      <br />
      <Dropdown
        v-model="dispositivoSelecionado3"
        :options="dispositivos"
        optionLabel="numeroSerie"
        placeholder="Selecione o dispositivo da Fase C"
        style="width: 100%"
      />
      <br />
      <br />
      <br />
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
import DispositivoService from "@/service/DispositivoService";
import LinhaService from "@/service/LinhaService";

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
      dispositivoSelecionado1: {
        numeroSerie: null,
        modelo: null,
        status: null,
      },
      dispositivoSelecionado2: {
        numeroSerie: null,
        modelo: null,
        status: null,
      },
      dispositivoSelecionado3: {
        numeroSerie: null,
        modelo: null,
        status: null,
      },
      dispositivos: null,
      dispositivosFiltrados: null,
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
  dispositivoService: null,
  linhaService: null,
  created() {
    this.estruturaService = new EstruturaService();
    this.dispositivoService = new DispositivoService();
    this.linhaService = new LinhaService();
  },
  mounted() {
    this.linhaId = parseInt(localStorage.getItem("linhaId"));
    this.getAll();
    this.getDispositivos();
    this.filtrarDispositivos();
    console.log(this.dispositivosFiltrados);
  },
  methods: {
    showSaveModal() {
      this.displayModal = true;
    },
    showEditModal() {
      this.displayModal = true;
    },
    getAll() {
      this.estruturaService
        .getEstruturasLinha(parseInt(localStorage.getItem("linhaId")))
        .then((data) => {
          this.estruturas = data.data;
        });
    },
    save() {
      /* this.estrutura.faseA = this.dispositivoSelecionado1;
      this.estrutura.faseB = this.dispositivoSelecionado2;
      this.estrutura.faseC = this.dispositivoSelecionado3; */

      if (
        !(
          this.dispositivoSelecionado1.numeroSerie &&
          this.dispositivoSelecionado2.numeroSerie &&
          this.dispositivoSelecionado3.numeroSerie
        )
      ) {
        this.$toast.add({
          severity: "error",
          summary: "Adição não concluída",
          detail: "Não pode haver fases vazias",
          life: 3000,
        });
        return;
      }

      if (
        this.dispositivoSelecionado1.numeroSerie ===
          this.dispositivoSelecionado2.numeroSerie ||
        this.dispositivoSelecionado1.numeroSerie ===
          this.dispositivoSelecionado3.numeroSerie ||
        this.dispositivoSelecionado2.numeroSerie ===
          this.dispositivoSelecionado3.numeroSerie
      ) {
        this.$toast.add({
          severity: "error",
          summary: "Adição não concluída",
          detail: "Não pode haver dispositivos repetidos por fase",
          life: 3000,
        });
        return;
      }

      this.estruturaService.save(this.estrutura).then((data) => {
        if (data.status === 200) {
          this.estrutura = data.data;
          var dtoED = this.makeDtoEstruturaDispositivos(
            this.estrutura.id,
            this.dispositivoSelecionado1.numeroSerie,
            this.dispositivoSelecionado2.numeroSerie,
            this.dispositivoSelecionado3.numeroSerie
          );
          this.estruturaService.addDispositivos(dtoED).then((data) => {
            if (data.status === 200) {
              this.estrutura = data.data;
              var dtoLE = this.makeDtoLinhaEstrutura(
                this.linhaId,
                this.estrutura.id
              );
              this.linhaService.addEstrutura(dtoLE).then((data) => {
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
            }
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
      this.dispositivoSelecionado1 = {
        numeroSerie: null,
        modelo: null,
        status: null,
      };
      this.dispositivoSelecionado2 = {
        numeroSerie: null,
        modelo: null,
        status: null,
      };
      this.dispositivoSelecionado3 = {
        numeroSerie: null,
        modelo: null,
        status: null,
      };
    },
    getDispositivos() {
      this.dispositivoService.getLivres().then((data) => {
        if (data.status === 200) {
          this.dispositivos = data.data;
        }
      });
    },
    filtrarDispositivos() {
      this.dispositivosFiltrados = [];
      for (let dispositivo in this.dispositivos) {
        if (
          dispositivo != this.dispositivoSelecionado1 ||
          dispositivo != this.dispositivoSelecionado2 ||
          dispositivo != this.dispositivoSelecionado3
        ) {
          this.dispositivosFiltrados.push(dispositivo);
        }
      }
    },
    makeDtoLinhaEstrutura(idLinha, idEstrutura) {
      return {
        idLinha: idLinha,
        idEstrutura: idEstrutura,
      };
    },
    makeDtoEstruturaDispositivos(
      idEstrutura,
      numeroSerieFaseA,
      numeroSerieFaseB,
      numeroSerieFaseC
    ) {
      return {
        idEstrutura: idEstrutura,
        numeroSerieFaseA: numeroSerieFaseA,
        numeroSerieFaseB: numeroSerieFaseB,
        numeroSerieFaseC: numeroSerieFaseC,
      };
    },
  },
};
</script>

<style>
</style>