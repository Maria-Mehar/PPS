function performOperation() {
    const num1 = parseFloat(document.getElementById('no1').value);
    const num2 = parseFloat(document.getElementById('no2').value);
    const operation = document.getElementById('operation').value;
  
    let result;
  
    if (isNaN(num1) || isNaN(num2)) {
      result = "Please enter valid numbers.";
    } else {
      switch (operation) {
        case "add":
          result = num1 + num2;
          break;
        case "subtract":
          result = num1 - num2;
          break;
        case "multiply":
          result = num1 * num2;
          break;
        case "divide":
          if (num2 === 0) {
            result = "Division by zero is not allowed.";
          } else {
            result = num1 / num2;
          }
          break;
        default:
          result = "Invalid operation.";
      }
    }
  
    document.getElementById('result').textContent = `Result: ${result}`;
  }
  
