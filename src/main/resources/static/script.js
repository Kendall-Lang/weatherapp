async function getWeather() {
    const city = document.getElementById("cityInput").value;
    const resultDiv = document.getElementById("weatherResult");
    resultDiv.innerHTML = "Loading...";

    try {
        const response = await fetch(`/weather?city=${encodeURIComponent(city)}`);
        if (!response.ok) {
            throw new Error("City could not be found or Server Error");
        }

        const data = await response.json();
        console.log(data); // for debugging
        displayWeather(data);
    } catch (error) {
        resultDiv.innerHTML = `<p class="error">${error.message}</p>`;
    }
}

function displayWeather(data) {
    const resultDiv = document.getElementById("weatherResult");
    resultDiv.innerHTML = `
        <h2>${data.name}</h2>
        <p>Temperature: ${data.main.temp}°C</p>
        <p>Condition: ${capitalizeFirstLetter(data.weather[0].description)}</p>
        <p>Humidity: ${data.main.humidity}%</p>
    `;
}

function capitalizeFirstLetter(string) {
	return string.charAt(0).toUpperCase() + string.slice(1);
}_min