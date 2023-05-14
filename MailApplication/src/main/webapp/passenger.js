const addPassengerBtn = document.getElementById('add-passenger-btn');
const passengerDetailsTable = document.getElementById('passenger-details-table');
let passengerCount = 1;

addPassengerBtn.addEventListener('click', () => {
  passengerCount++;

  const passengerDetailsRow = document.createElement('tr');
  passengerDetailsRow.innerHTML = `
    <td>
      <label><b>Passenger Details-${passengerCount}</b></label>
    </td>
  `;
  passengerDetailsTable.appendChild(passengerDetailsRow);

  const nameInput = document.createElement('input');
  nameInput.setAttribute('type', 'text');
  nameInput.setAttribute('name', `p${passengerCount}_name`);
  nameInput.setAttribute('placeholder', 'Enter full name');
  nameInput.style.marginLeft = '25px';

  const ageInput = document.createElement('input');
  ageInput.setAttribute('type', 'number');
  ageInput.setAttribute('name', `p${passengerCount}_age`);
  ageInput.setAttribute('placeholder', 'Enter age of the passenger');
  ageInput.style.marginLeft = '25px';

  const genderLabel = document.createElement('label');
  genderLabel.innerHTML = '<b>Gender</b>';

  const maleInput = document.createElement('input');
  maleInput.setAttribute('type', 'radio');
  maleInput.setAttribute('name', `p${passengerCount}_sex`);
  maleInput.setAttribute('value', 'Male');
  maleInput.setAttribute('id', `gender-${passengerCount}`);
  maleInput.style.marginLeft = '25px';

  const maleLabel = document.createElement('label');
  maleLabel.setAttribute('for', `gender-${passengerCount}`);
  maleLabel.textContent = 'Male';
  maleLabel.style.marginRight = '10px';

  const femaleInput = document.createElement('input');
  femaleInput.setAttribute('type', 'radio');
  femaleInput.setAttribute('name', `p${passengerCount}_sex`);
  femaleInput.setAttribute('value', 'Female');
  femaleInput.setAttribute('id', `gender-${passengerCount}`);
  femaleInput.style.marginLeft = '25px';

  const femaleLabel = document.createElement('label');
  femaleLabel.setAttribute('for', `gender-${passengerCount}`);
  femaleLabel.textContent = 'Female';
  femaleLabel.style.marginRight = '10px';

  const otherInput = document.createElement('input');
  otherInput.setAttribute('type', 'radio');
  otherInput.setAttribute('name', `p${passengerCount}_sex`);
  otherInput.setAttribute('value', 'Other');
  otherInput.setAttribute('id', `gender-${passengerCount}`);
  otherInput.style.marginLeft = '25px';

  const otherLabel = document.createElement('label');
  otherLabel.setAttribute('for', `gender-${passengerCount}`);
  otherLabel.textContent = 'Other';

  const genderInputs = [maleInput, femaleInput, otherInput];
  const genderLabels = [maleLabel, femaleLabel, otherLabel];

  genderInputs.forEach((input) => {
    genderLabel.appendChild(input);
  });

  genderLabels.forEach((label) => {
    genderLabel.appendChild(label);
  });

  const passengerDetailsInputs = document.createElement('td');
  passengerDetailsInputs.appendChild(nameInput);
  passengerDetailsInputs.appendChild(ageInput);
  passengerDetailsInputs.appendChild(genderLabel);

  const passengerDetailsInputsRow = document.createElement('tr');
  passengerDetailsInputsRow.appendChild(passengerDetailsInputs);

  passengerDetailsTable.appendChild(passengerDetailsInputsRow);
});
