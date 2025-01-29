// Updated JavaScript for the To-Do List Application

const taskInput = document.getElementById('task-input');
const addTaskBtn = document.getElementById('add-task-btn');
const taskList = document.getElementById('task-list');

// Load tasks from local storage
document.addEventListener('DOMContentLoaded', loadTasks);

// Add task button click event
addTaskBtn.addEventListener('click', handleAddTask);

// Task list event delegation for marking as completed and deleting tasks
taskList.addEventListener('click', (e) => {
    const target = e.target;

    if (target.tagName === 'SPAN') {
        const taskItem = target.parentElement;
        taskItem.classList.toggle('completed');
        updateTaskCompletion(target.textContent);
    }

    if (target.classList.contains('delete-btn')) {
        const taskItem = target.parentElement;
        taskItem.classList.add('fade-out');
        setTimeout(() => {
            taskItem.remove();
            removeTaskFromLocalStorage(taskItem.querySelector('span').textContent);
        }, 300); // Match the fade-out transition duration
    }
});

// Handle adding a new task
function handleAddTask() {
    const taskText = taskInput.value.trim();

    if (!taskText) {
        alert('Task cannot be empty!');
        return;
    }

    if (isDuplicateTask(taskText)) {
        alert('Task already exists!');
        return;
    }

    addTask(taskText);
    saveTaskToLocalStorage(taskText);
    taskInput.value = '';
}

// Check if the task already exists
function isDuplicateTask(taskText) {
    const tasks = JSON.parse(localStorage.getItem('tasks')) || [];
    return tasks.some(task => task.text === taskText);
}

// Add a new task to the DOM
function addTask(taskText, completed = false) {
    const taskItem = document.createElement('li');
    taskItem.classList.add('fade-in');
    if (completed) taskItem.classList.add('completed');
    taskItem.innerHTML = `
        <span>${taskText}</span>
        <button class="delete-btn">Delete</button>
    `;
    taskList.appendChild(taskItem);
}

// Save task to local storage
function saveTaskToLocalStorage(taskText) {
    const tasks = JSON.parse(localStorage.getItem('tasks')) || [];
    tasks.push({ text: taskText, completed: false });
    localStorage.setItem('tasks', JSON.stringify(tasks));
}

// Load tasks from local storage
function loadTasks() {
    const tasks = JSON.parse(localStorage.getItem('tasks')) || [];
    tasks.forEach(task => addTask(task.text, task.completed));
}

// Remove task from local storage
function removeTaskFromLocalStorage(taskText) {
    let tasks = JSON.parse(localStorage.getItem('tasks')) || [];
    tasks = tasks.filter(task => task.text !== taskText);
    localStorage.setItem('tasks', JSON.stringify(tasks));
}

// Update task completion in local storage
function updateTaskCompletion(taskText) {
    const tasks = JSON.parse(localStorage.getItem('tasks')) || [];
    const task = tasks.find(task => task.text === taskText);
    if (task) task.completed = !task.completed;
    localStorage.setItem('tasks', JSON.stringify(tasks));
}
