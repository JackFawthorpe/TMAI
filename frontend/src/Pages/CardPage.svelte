<script lang="ts">

    import {API} from "../apis/API";
    import CardCard from "../Components/CardCard.svelte";

    let cards: Card[];
    const loadCards = async () => {
        cards = await API.getAllCards();
    }

    const promise = loadCards();

</script>


{#await promise}
    <p>...waiting</p>
{:then _}
    <ul>
        {#each cards as card}
            <CardCard card={card}/>
        {/each}
    </ul>
{:catch error}
    <p style="color: red">{error.message}</p>
{/await}