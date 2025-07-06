const apiUrl = '/api/tasks';

async function fetchTasks() {
  const res = await fetch(apiUrl);
  const data = await res.json();
  const tasks = data.result;

  const list = document.getElementById('task-list');
  list.innerHTML = '';

  tasks.forEach(task => {
    const li = document.createElement('li');
    li.className = task.completed ? 'completed' : '';

    // Tên task
    const span = document.createElement('span');
    span.textContent = task.title;
    span.onclick = () => toggleTask(task.id);

    // Nút sửa
    const editBtn = document.createElement('button');
    editBtn.textContent = '✏️';
    editBtn.onclick = (e) => {
      e.stopPropagation(); // Không bị toggle khi click nút
      editTask(task.id, task.title);
    };

    // Nút xoá
    const deleteBtn = document.createElement('button');
    deleteBtn.textContent = '🗑️';
    deleteBtn.onclick = (e) => {
      e.stopPropagation();
      deleteTask(task.id);
    };

    // Thêm vào li
    li.appendChild(span);
    li.appendChild(editBtn);
    li.appendChild(deleteBtn);

    list.appendChild(li);
  });
}

async function addTask() {
  const input = document.getElementById('new-task');
  const title = input.value.trim();
  if (!title) return;

  await fetch(apiUrl, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title })
  });

  input.value = '';
  fetchTasks();
}

async function toggleTask(id) {
  await fetch(`${apiUrl}/${id}/toggle`, { method: 'PUT' });
  fetchTasks();
}

async function deleteTask(id) {
  if (confirm("Xóa task này?")) {
    await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
    fetchTasks();
  }
}

async function editTask(id, oldTitle) {
  const newTitle = prompt("Sửa nội dung task:", oldTitle);
  if (newTitle === null || newTitle.trim() === '' || newTitle === oldTitle) return;

  await fetch(`${apiUrl}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title: newTitle })
  });

  fetchTasks();
}

document.addEventListener('DOMContentLoaded', fetchTasks);
