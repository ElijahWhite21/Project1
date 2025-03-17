// Define card types: Pokémon, Trainer, and Energy

// Create a random card from the list of Pokémon, Trainer, and Energy cards
// Each card has a name, image, type, and stats (for Pokémon cards)
// Pokémon cards with their respective stats
const POKEMON_CARDS = [
    {name : 'Pikachu', image: `images/cards/Pikachu.png`, type: 'pokemon', hp: 70, attack: 30, defense: 20},
    {name : 'Charmander', image: `images/cards/Charmander.png`, type: 'pokemon', hp: 70, attack: 40, defense: 30},
    {name : 'Bulbasaur', image: `images/cards/Bulbasaur.png`, type: 'pokemon', hp: 70, attack: 20, defense: 30},
    {name : 'Squirtle', image: `images/cards/Squirtle.png`, type: 'pokemon', hp: 70, attack: 20, defense: 40},
    {name : 'Eevee', image: `images/cards/Eevee.png`, type: 'pokemon', hp: 60, attack: 30, defense: 20},
    {name : 'Mewtwo', image: `images/cards/Mewtwo.png`, type: 'pokemon', hp: 120, attack: 100, defense: 30},
    {name : 'Lugia', image: `images/cards/Lugia.png`, type: 'pokemon', hp: 80, attack: 20, defense: 70},
    {name : 'Rayquaza', image: `images/cards/Rayquaza.png`, type: 'pokemon', hp: 100, attack: 100, defense: 40},
    {name : 'Snorlax', image: `images/cards/Snorlax.png`, type: 'pokemon', hp: 160, attack: 120, defense: 100},
    {name : 'Lucario', image: `images/cards/Lucario.png`, type: 'pokemon', hp: 100, attack: 60, defense: 40}
];

// Trainer cards for different effects
// These cards do not have stats like Pokémon cards 
// but have unique effects in the game
// For simplicity, we will just define their names and images
const TRAINER_CARDS = [
    {name : 'Potion', image: `images/cards/PotionTrainer.png`, type: 'trainer'},
    {name : 'Energy Search', image: `images/cards/EnergySearch.png`, type: 'trainer'},
    {name : 'Professor Research', image: `images/cards/ProfessorTrainer.png`, type: 'trainer'},
    {name : 'Poké Ball', image: `images/cards/PokeTrainer.png`, type: 'trainer'}
];

// Energy cards for different types
// These cards are used to power Pokémon attacks
// Each energy card has a name, image, and type
// For simplicity, we will just define their names and images
const ENERGY_CARDS = [
    {name : 'Lighting Energy', image: `images/cards/LightingEnergy.png`, type: 'energy'},
    {name : 'Fire Energy', image: `images/cards/FireEnergy.png`, type: 'energy'},
    {name : 'Water Energy', image: `images/cards/WaterEnergy.png`, type: 'energy'},
    {name : 'Grass Energy', image: `images/cards/GrassEnergy.png`, type: 'energy'},
    {name : 'Metal Energy', image: `images/cards/MetalEnergy.png`, type: 'energy'},
    {name : 'Psychic Energy', image: `images/cards/PsychicEnergy.png`, type: 'energy'},
    {name : 'Fighting Energy', image: `images/cards/FightingEnergy.png`, type: 'energy'},
    {name : 'Darkness Energy', image: `images/cards/DarknessEnergy.png`, type: 'energy'},
    {name : 'Fairy Energy', image: `images/cards/FairyEnergy.png`, type: 'energy'}
];

// Game Data: Structure for players, their hands, active Pokémon, and deck
// Each player has a hand of cards, an active Pokémon, a deck of cards, and prize cards
const players = {
    player1: {
        hand: [],
        activePokemon: null,
        deck: [],
        prizeCards: 0,  // Track prize cards drawn
        drawCard: function() {
            const newCard = createCard();
            this.hand.push(newCard);
            updatePlayerHand('player1');
        },
        playPokemon: function(cardIndex) {
            const card = this.hand[cardIndex];
            this.activePokemon = card;
            this.hand.splice(cardIndex, 1); // Remove from hand
            updateActivePokemon('player1');
            updatePlayerHand('player1');
        }
    },
    player2: {
        hand: [],
        activePokemon: null,
        deck: [],
        prizeCards: 0,
        drawCard: function() {
            const newCard = createCard();
            this.hand.push(newCard);
            updatePlayerHand('player2');
        },
        playPokemon: function(cardIndex) {
            const card = this.hand[cardIndex];
            this.activePokemon = card;
            this.hand.splice(cardIndex, 1); // Remove from hand
            updateActivePokemon('player2');
            updatePlayerHand('player2');
        }
    }
};

// Create a shuffled deck for each player
// The deck consists of a random number of Pokémon, Trainer, and Energy cards
// The number of cards is randomized to create a unique deck each time
// The deck is shuffled to randomize the order of the cards
// The deck is created by calling the createDeck function
function createDeck() {
    let deck = [];

     // Add Pokémon cards from the predefined POKEMON_CARDS array
     // The number of Pokémon cards is randomized between 15 and 20
     let numPokemonCards = Math.floor(Math.random() * 6) + 15; // Random number between 15 and 20
     for (let i = 0; i < numPokemonCards; i++) {
         const randomCard = POKEMON_CARDS[Math.floor(Math.random() * POKEMON_CARDS.length)];
         deck.push({ 
             type: 'pokemon', 
             name: randomCard.name, 
             image: randomCard.image, 
             hp: randomCard.hp, 
             attack: randomCard.attack, 
             defense: randomCard.defense 
         });
     }
 
     // Add Trainer cards from the predefined TRAINER_CARDS array
     // The number of Trainer cards is randomized between 20 and 25
     let numTrainerCards = Math.floor(Math.random() * 6) + 20; // Random number between 20 and 25
     for (let i = 0; i < numTrainerCards; i++) {
         const trainerCard = TRAINER_CARDS[Math.floor(Math.random() * TRAINER_CARDS.length)];
         deck.push({
             type: 'trainer', 
             name: trainerCard.name, 
             image: trainerCard.image 
         });
     }
 
     // Add Energy cards from the predefined ENERGY_CARDS array
     // The number of Energy cards is randomized between 15 and 20
     let numEnergyCards = Math.floor(Math.random() * 6) + 15; // Random number between 15 and 20
     for (let i = 0; i < numEnergyCards; i++) {
         const energyCard = ENERGY_CARDS[Math.floor(Math.random() * ENERGY_CARDS.length)];
         deck.push({
             type: 'energy', 
             name: energyCard.name, 
             image: energyCard.image
         });
     }
 
     // Shuffle the deck
     deck = shuffle(deck);
     return deck;
}

// Shuffle function to randomize the deck
// This function takes an array as input and shuffles it in place using the Fisher-Yates algorithm
// It iterates through the array from the last element to the first, swapping each element with a randomly chosen element that comes before it (including itself)
function shuffle(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]]; // Swap elements
    }
    return array;
}

// Draw 7 cards from the deck and give them to the player
// This function creates a deck for the player, draws 7 cards from it, and updates the player's hand
// The remaining cards in the deck are also updated
function drawCards(player) {
    const deck = createDeck();
    players[player].deck = deck;
    players[player].hand = deck.slice(0, 7); // Draw 7 cards
    players[player].deck = deck.slice(7); // Remaining cards in the deck
    updatePlayerHand(player);
}

// Initialize the game by setting up the decks and dealing cards
// This function is called when the game starts to set up the players and their hands
// It draws 7 cards for each player and updates their hands
function startGame() {
    drawCards('player1');
    drawCards('player2');
    alert("Game started! Both players have drawn 7 cards.");
}

// Update the player's hand display (with image on click)
// This function takes a player identifier (e.g., 'player1' or 'player2')
// and updates the corresponding hand display in the UI
function updatePlayerHand(player) {
    const handDiv = document.getElementById(`${player}-hand`);
    handDiv.innerHTML = ''; // Clear the hand first

    players[player].hand.forEach((card, index) => {
        const cardDiv = document.createElement('div');
        cardDiv.classList.add('card');
        cardDiv.textContent = card.name; // This will display the card name

        // When clicked, show the card image and stats
        cardDiv.onclick = () => showCardImage(card);  

        // Setting custom data attributes for index and player to handle card selection
        cardDiv.setAttribute('data-index', index);
        cardDiv.setAttribute('data-player', player);
        handDiv.appendChild(cardDiv);
    });
}

// Create a card from one of the three decks
// This function randomly selects a card from the Pokémon, Trainer, or Energy decks
// It uses Math.random() to choose a deck and then selects a random card from that deck
function createCard() {
    // Randomly select a deck: Pokemon, Trainer, or Energy
    const randomDeck = Math.floor(Math.random() * 3);

    let selectedCard;

    // Select a random card from the chosen deck
    if (randomDeck === 0) {
        // Pokemon deck
        selectedCard = POKEMON_CARDS[Math.floor(Math.random() * POKEMON_CARDS.length)];
    } else if (randomDeck === 1) {
        // Trainer deck
        selectedCard = TRAINER_CARDS[Math.floor(Math.random() * TRAINER_CARDS.length)];
    } else {
        // Energy deck
        selectedCard = ENERGY_CARDS[Math.floor(Math.random() * ENERGY_CARDS.length)];
    }

    return selectedCard;  // Return the selected card
}

// Function to remove the image for a given Pokémon name
// This function searches for the image in the battle area and removes it
// It uses the alt attribute of the image to match the Pokémon name
function removePokemonImage(pokemonName) {
    // Get the div that contains the images
    const pokemonDiv = document.getElementById('battle-area');

    if (pokemonDiv) {
        // Find all image tags inside the div
        const images = pokemonDiv.querySelectorAll('img');

        // Loop through the images and find the one with the matching name in the alt attribute
        images.forEach(img => {
            if (img.alt === pokemonName) {
                pokemonDiv.removeChild(img);  // Remove the image from the div
                console.log(`Removed image for ${pokemonName}`);
            }
        });
    } else {
        console.log('Div not found');
    }
}


// Player 1 button event listener for drawing a card
// This button allows Player 1 to draw a card from their deck
// When clicked, it calls the drawCard method of Player 1
const drawButton = document.getElementById('btnPlayer1_drawCard');
drawButton.addEventListener('click', function() {
    players.player1.drawCard();
});

// Player 1 button event listener for playPokémon
// This button allows Player 1 to play a Pokémon card from their hand
// When clicked, it moves the selected card to the battle area and updates the player's hand
// It also removes the card from the player's hand and updates the active Pokémon
const playbutton = document.getElementById('btnPlayer1_playPokemon');
playbutton.addEventListener('click', function() {
    const card = document.getElementById('player1-active').children[0];
    moveImageToBattle(card);

    // Update player 1 hand to remove card from hand 
    const cardName = card.getAttribute('alt'); // Get the card index
    
    // Find the first instance of the card index
    const cardIndex = players.player1.hand.findIndex(card => card.name === cardName);


    players.player1.playPokemon(cardIndex);

    // Remove card and set img src to null
    const imgElement = document.getElementById('card-image').querySelector('img'); // Get the <img> inside the card
    imgElement.src = "";  // Clear the image source

});

// Player 1 button event listener for choosePokémon 
// This button allows Player 1 to choose a Pokémon card from their hand
// When clicked, it moves the selected card to Player 1's active area and updates the card image
// It also removes the card from the player's hand and updates the active Pokémon
// The card image is cleared after the Pokémon is chosen
const choosebutton = document.getElementById('btnPlayer1_choosePokemon');
choosebutton.addEventListener('click', function() {
    const card = document.getElementById('card-image').children[0];
    moveImageForPlayer1(card);

    // Remove card and set img src to null 
    const imgElement = document.getElementById('card-image').querySelector('img'); // Get the <img> inside the card
    imgElement.src = "";  // Clear the image source
});

// Player 1 button event listener for retreatPokémon
// This button allows Player 1 to retreat their active Pokémon
// When clicked, it removes the active Pokémon image from the battle area
// It also updates the player's hand to add the card back and sets the active Pokémon to null
// Finally, it updates the active Pokémon display and the player's hand
const retreatbutton = document.getElementById('btnPlayer1_retreatPokemon');
retreatbutton.addEventListener('click', function() {
    const card = document.getElementById('battle-area').children[0];
    removePokemonImage(card.alt);

    // Update player 1 hand to add card back to hand 
    players.player1.hand.push(players.player1.activePokemon);
    players.player1.activePokemon = null;
    updateActivePokemon('player1');
    updatePlayerHand('player1');
});

// Player 2 button event listener for drawing a card
// This button allows Player 2 to draw a card from their deck
// When clicked, it calls the drawCard method of Player 2
// It updates Player 2's hand to reflect the drawn card
const drawButtonForPlayer2 = document.getElementById('btnPlayer2_drawCard');
drawButtonForPlayer2.addEventListener('click', function() {
    players.player2.drawCard();
});


// Player 2 button event listener for playPokémon
// This button allows Player 2 to play a Pokémon card from their hand
// When clicked, it moves the selected card to the battle area and updates Player 2's hand
// It also removes the card from Player 2's hand and updates the active Pokémon
const playbutton2 = document.getElementById('btnPlayer2_playPokemon');
playbutton2.addEventListener('click', function() {
    const card = document.getElementById('player2-active').children[0];
    moveImageToBattleForPlayer2(card);

    // Update player 2 hand to remove card from hand 
    const cardName = card.getAttribute('alt'); // Get the card index
    
    // Find the first instance of the card index
    const cardIndex = players.player2.hand.findIndex(card => card.name === cardName);


    players.player2.playPokemon(cardIndex);


    // Remove card and set img src to null
    const imgElement = document.getElementById('card-image ').querySelector('img'); // Get the <img> inside the card
    imgElement.src = "";  // Clear the image source
});

// Player 2 button event listener for choosePokémon
// This button allows Player 2 to choose a Pokémon card from their hand
// When clicked, it moves the selected card to Player 2's active area and updates the card image
// It also removes the card from Player 2's hand and updates the active Pokémon
// The card image is cleared after the Pokémon is chosen
const choosebutton2 = document.getElementById('btnPlayer2_choosePokemon');
choosebutton2.addEventListener('click', function() {
    const card = document.getElementById('card-image').children[0];
    moveImageForPlayer2(card);

    // Remove card and set img src to null
    const imgElement = document.getElementById('card-image').querySelector('img'); // Get the <img> inside the card
    imgElement.src = "";  // Clear the image source
});

// Function to move the image for Player 1 to the battle area
function moveImageToBattle(card) {
    const battleArea = document.getElementById('battle-area');
    battleArea.appendChild(card);
}                             

// Function to move the image for Player 1 to player 1's side
function moveImageForPlayer1(card) {
    const battleArea = document.getElementById('player1-active');
    battleArea.appendChild(card);
}                       

// Function to move the image for Player 2 to the battle area
function moveImageToBattleForPlayer2(card) {
    const battleArea = document.getElementById('battle-area');
    battleArea.appendChild(card);
}

// Function to move the image for Player 2 to player 2's side
function moveImageForPlayer2(card) {
    const battleArea = document.getElementById('player2-active');
    battleArea.appendChild(card);
}


// Show the card stats when clicked
// This function creates a div element to display the card stats
// It sets the inner HTML to include the card name, type, and stats
// The stats are displayed for 5 seconds before the div is removed from the document
function showCardStats(card) {
    const statsDiv = document.createElement('div');
    statsDiv.classList.add('card-stats');
    statsDiv.innerHTML = `<h3>${card.name}</h3><p>Type: ${card.type}</p><p>HP: 100</p><p>Attack: 50</p><p>Defense: 30</p>`;
    document.body.appendChild(statsDiv);
    statsDiv.style.display = 'block';

    // Close the stats modal
    setTimeout(() => {
        document.body.removeChild(statsDiv);
    }, 5000); // Stats disappear after 5 seconds
}


let currentCard = null; // Track the current card being dragged

// Function to show the image and stats when a card is clicked
// This function creates a modal-like display for the card image and stats
// It sets the image source and displays the card properties (name, type, HP, attack, defense)
// The modal is displayed when a card is clicked and hidden when the user clicks outside of it
function showCardImage(card) {
    const imageDiv = document.getElementById('card-image');
    const statsDiv = document.getElementById('card-stats');
    const container = document.getElementById('card-image-container');

    // Clear previous content
    imageDiv.innerHTML = '';
    statsDiv.innerHTML = '';

    // Create image element and set source
    const img = document.createElement('img');
    img.src = card.image; // Set image source for the card
    img.alt = card.name;
    img.style.width = '150px';  // Adjust size as needed
    img.style.height = '200px';
    img.setAttribute('draggable', true); // Make image draggable
    imageDiv.appendChild(img);

    // Ensure the card properties exist and render them
    statsDiv.innerHTML = `
       <h4>${card.name}</h4>
        <p>Type: ${card.type}</p>
        <p>HP: ${card.hp !== undefined ? card.hp : 'N/A'}</p>
        <p>Attack: ${card.attack !== undefined ? card.attack : 'N/A'}</p>
        <p>Defense: ${card.defense !== undefined ? card.defense : 'N/A'}</p>
    `;

    currentCard = card; // Set the current card for drag-and-drop

    // Display the modal
    container.style.display = 'block';
}

// Function to hide the card image and stats
function hideCardImage() {
    const container = document.getElementById('card-image-container');
    container.style.display = 'none';
}

// Allow the user to drag the card
document.addEventListener('dragstart', (e) => {
    if (e.target && e.target.nodeName === 'IMG') {
        // Make sure that we're dragging the image element
        currentCard = { ...currentCard, dragElement: e.target }; // Save the image and its properties
    }
});

// Drag-and-drop logic for moving Pokémon card to the battle area
const battleArea1 = document.getElementById('player1-battle');
const battleArea2 = document.getElementById('player2-battle');

// Allow for dragging over battle area
//battleArea1.addEventListener('dragover', (e) => e.preventDefault());
//battleArea2.addEventListener('dragover', (e) => e.preventDefault());

// Handle drop event for player 1's battle area
battleArea1.addEventListener('drop', (e) => {
    e.preventDefault();
    if (currentCard) {
        // Create the card element inside the battle area
        const cardDiv = document.createElement('div');
        cardDiv.classList.add('card');
        cardDiv.innerHTML = `<img src="${currentCard.image}" alt="${currentCard.name}" />`;

        // Add stats for the dropped card
        const statsDiv = document.createElement('div');
        statsDiv.classList.add('card-stats');
        statsDiv.innerHTML = `
            <h4>${currentCard.name}</h4>
            <p>Type: ${currentCard.type}</p>
            <p>HP: ${currentCard.hp}</p>
            <p>Attack: ${currentCard.attack}</p>
            <p>Defense: ${currentCard.defense}</p>
        `;

        // Append to the battle area
        battleArea1.appendChild(cardDiv);
        battleArea1.appendChild(statsDiv);
    }

    // Reset the card
    hideCardImage();
});

// Handle drop event for player 2's battle area
battleArea2.addEventListener('drop', (e) => {
    e.preventDefault();
    if (currentCard) {
        // Create the card element inside the battle area
        const cardDiv = document.createElement('div');
        cardDiv.classList.add('card');
        cardDiv.innerHTML = `<img src="${currentCard.image}" alt="${currentCard.name}" />`;

        // Add stats for the dropped card
        const statsDiv = document.createElement('div');
        statsDiv.classList.add('card-stats');
        statsDiv.innerHTML = `
            <h4>${currentCard.name}</h4>
            <p>Type: ${currentCard.type}</p>
            <p>HP: ${currentCard.hp}</p>
            <p>Attack: ${currentCard.attack}</p>
            <p>Defense: ${currentCard.defense}</p>
        `;

        // Append to the battle area
        battleArea2.appendChild(cardDiv);
        battleArea2.appendChild(statsDiv);
    }

    // Reset the card
    hideCardImage();
});

// To handle drag start event on the card
document.addEventListener('dragstart', function (e) {
    if (e.target.tagName === "IMG") {
        currentCard = { ...currentCard, dragElement: e.target };
    }
});

// Update active Pokémon display
function updateActivePokemon(player) {
    const activePokemonDiv = document.getElementById(`${player}-active`);
    activePokemonDiv.innerHTML = '';  // Clear previous Pokémon
    const activePokemon = players[player].activePokemon;
    if (activePokemon) {
        const pokemonDiv = document.createElement('div');
        pokemonDiv.textContent = activePokemon.textContent;  // Display Pokémon name
        activePokemonDiv.appendChild(pokemonDiv);
    }
}
