<template>
  <div style="margin: 0 auto; width: 50%">
    <div class="name">
      <h1>Gerenciador de Dispositivos</h1>
    </div>

    <div class="foto">
      <img src="../assets/Nastek.png" alt="Italian Trulli" />
    </div>
    <div class="buttons">
      <Button
        label="Clientes"
        icon="pi pi-user"
        @click="$router.push('listaClientes')"
        class="p-button-text"
      />

      <Button
        label="Dispositivos"
        icon="pi pi-sitemap"
        @click="$router.push('listaDispositivos')"
        class="p-button-text"
      />
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.name {
  display: block;
  margin: 180px 140px 0 ;
  width: 100%;
  color: #007ad9;
}

.foto {
  width: 50%;
  margin: 0 225px 0;
  display: block;
}

.buttons {
  width: 50%;
  margin: 0 195px 0;
  display: block;
}
</style>