function billingInformation() {
    const siName = document.getElementById('siName');
    const siZip = document.getElementById('siZip');
    const biName = document.getElementById('biName');
    const biZip = document.getElementById('biZip');
    const checkbox = document.getElementById('sameAsShipping');

    // If the checkbox is checked, copy shipping info to billing info
    if (checkbox.checked) {
      biName.value = siName.value;
      biZip.value = siZip.value;
    } else {
      // If unchecked, clear billing info fields
      biName.value = '';
      biZip.value = '';
    }
  }
