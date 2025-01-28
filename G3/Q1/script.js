function calculate(operation) {
    const input1 = document.getElementById('input1').value;
    const input2 = document.getElementById('input2').value;
    const error = document.getElementById('error');
    const output = document.getElementById('output');

    // Reset error message and output
    error.textContent = '';
    output.value = '';

    // Validate inputs
    if (input1 === '' || input2 === '') {
        error.textContent = 'Both input fields are mandatory.';
        return;
    }

    const num1 = parseFloat(input1);
    const num2 = parseFloat(input2);

    if (num1 === num2) {
        error.textContent = 'The values of both fields must not be the same.';
        return;
    }

    let result;

    switch (operation) {
        case 'sum':
            result = num1 + num2;
            break;
        case 'subtract':
            if (num1 <= num2) {
                error.textContent = 'In subtraction, the first number must be greater than the second number.';
                return;
            }
            result = num1 - num2;
            break;
        case 'multiply':
            result = num1 * num2;
            break;
        case 'divide':
            if (num2 === 0) {
                error.textContent = 'Division by zero is not allowed.';
                return;
            }
            result = num1 / num2;
            break;
        default:
            error.textContent = 'Invalid operation.';
            return;
    }

    // Display result
    output.value = result;
}
