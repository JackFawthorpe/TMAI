<script lang="ts">

    import {API} from "../apis/API";
    import CardCard from "../Components/CardCard.svelte";
    import Loader from "../Components/Layout/Loader.svelte";
    import type {Card} from "../Types/Card";

    let cards: Card[];
    const loadCards = async () => {
        cards = await API.getAllCards();
    }

    const promise = loadCards();

</script>

{#await promise}
    <Loader/>
{:then _}
    <ul class="mx-2 pt-[60px] grid gap-y-5 gap-x-3 grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
        {#each cards as card}
            <CardCard card={card}/>
        {/each}
    </ul>
{:catch error}
    <p style="color: red">{error.message}</p>
{/await}