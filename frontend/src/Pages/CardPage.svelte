<script lang="ts">

    import {API} from "../apis/API";
    import CardCard from "../Components/CardCard.svelte";
    import Loader from "../Components/Layout/Loader.svelte";

    let cards: Card[];
    const loadCards = async () => {
        cards = await API.getAllCards();
    }

    const promise = loadCards();

</script>

{#await promise}
    <Loader/>
{:then _}
    <ul class="mx-2 grid mt-2 gap-y-5 gap-x-3 grid-cols-1 sm:grid-cols-2 lg:grid-cols-4">
        {#each cards as card}
            <CardCard card={card}/>
        {/each}
    </ul>
{:catch error}
    <p style="color: red">{error.message}</p>
{/await}