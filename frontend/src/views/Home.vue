<template>
    <div>
        <h1>Home</h1>
        <Button
          label="Clientes"
          icon="pi pi-times"
          @click="$router.push('listaClientes')"
          class="p-button-text"
        />
        <Button
          label="Linhas"
          icon="pi pi-times"
          @click="$router.push('listaLinhas')"
          class="p-button-text"
        />
        <Button
          label="Estruturas"
          icon="pi pi-times"
          @click="$router.push('listaEstruturas')"
          class="p-button-text"
        />
        <Button
          label="Dispositivos"
          icon="pi pi-times"
          @click="$router.push('listaDispositivos')"
          class="p-button-text"
        />
    </div>    
</template>

<script>
export default {

}

</script>

<style>

</style>