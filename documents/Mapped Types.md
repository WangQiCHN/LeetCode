在 TypeScript 中，**映射类型**（Mapped Types）是一种基于已有类型动态创建新类型的工具类型。它通过遍历一个类型的键（通常是联合类型或已有类型的键）来生成新的类型定义。映射类型通常与索引签名结合使用，允许开发者对类型的属性进行转换、约束或扩展。映射类型是 TypeScript 类型系统中强大而灵活的特性，广泛用于高级类型操作。

### 映射类型的定义
映射类型的语法如下：

```typescript
type NewType = {
  [Key in Type]: ValueType;
};
```

- `Key`：表示遍历的键，通常来自一个联合类型或已有类型的键（通过 `keyof` 提取）。
- `Type`：通常是一个联合类型或通过 `keyof` 获取的类型键集合。
- `ValueType`：为每个键指定的值类型，可以是固定类型或基于 `Key` 的动态类型。

### 映射类型的基本形式
映射类型通过 `in` 关键字遍历一个联合类型的键，并为每个键指定值类型。例如：

```typescript
type Keys = "name" | "age" | "email";

type User = {
  [K in Keys]: string;
};

// 等价于：
interface User {
  name: string;
  age: string;
  email: string;
}
```

在这个例子中，`User` 类型通过映射类型为每个键（`name`、`age`、`email`）分配了 `string` 类型。

### 映射类型的具体作用
映射类型在 TypeScript 编程中有以下几个主要作用：

1. **基于现有类型创建新类型**：
   映射类型可以基于现有类型的键生成新类型，通常用于转换属性。例如，将所有属性变为可选（`Partial` 的实现）：

   ```typescript
   type Partial<T> = {
     [K in keyof T]?: T[K];
   };

   interface User {
     name: string;
     age: number;
   }

   type PartialUser = Partial<User>;
   // 等价于：
   // type PartialUser = {
   //   name?: string;
   //   age?: number;
   // }
   ```

   这里，`keyof T` 获取 `User` 的键（`name` 和 `age`），然后通过映射类型为每个键添加 `?` 使其变成可选。

2. **属性修饰符的转换**：
   映射类型可以用来修改属性的修饰符，例如将属性变为只读（`Readonly` 的实现）或移除只读修饰符：

   ```typescript
   type Readonly<T> = {
     readonly [K in keyof T]: T[K];
   };

   interface User {
     name: string;
     age: number;
   }

   type ReadonlyUser = Readonly<User>;
   // 等价于：
   // type ReadonlyUser = {
   //   readonly name: string;
   //   readonly age: number;
   // }

   const user: ReadonlyUser = { name: "Alice", age: 30 };
   // user.name = "Bob"; // 错误：Cannot assign to 'name' because it is a read-only property.
   ```

3. **类型转换**：
   映射类型可以对值的类型进行转换。例如，将所有属性类型转换为 `string`：

   ```typescript
   type Stringify<T> = {
     [K in keyof T]: string;
   };

   interface User {
     name: string;
     age: number;
   }

   type StringifiedUser = Stringify<User>;
   // 等价于：
   // type StringifiedUser = {
   //   name: string;
   //   age: string;
   // }
   ```

4. **与索引签名结合**：
   映射类型可以与索引签名结合，创建动态键的类型。例如，基于一个联合类型生成索引类型：

   ```typescript
   type FeatureFlags = "darkMode" | "notifications" | "analytics";

   type FeatureConfig = {
     [K in FeatureFlags]: boolean;
   };

   const config: FeatureConfig = {
     darkMode: true,
     notifications: false,
     analytics: true,
   };
   ```

5. **条件类型与映射类型的结合**：
   映射类型可以与条件类型结合，实现更复杂的类型转换。例如，将特定类型的属性提取出来：

   ```typescript
   type PickByType<T, U> = {
     [K in keyof T as T[K] extends U ? K : never]: T[K];
   };

   interface User {
     name: string;
     age: number;
     email: string;
     active: boolean;
   }

   type StringFields = PickByType<User, string>;
   // 等价于：
   // type StringFields = {
   //   name: string;
   //   email: string;
   // }
   ```

   这里通过 `as` 关键字和条件类型过滤出值为 `string` 类型的属性。

6. **映射类型的内置工具类型**：
   TypeScript 提供了许多基于映射类型的内置工具类型，简化开发工作：
   - `Partial<T>`：将所有属性变为可选。
   - `Required<T>`：将所有属性变为必选。
   - `Readonly<T>`：将所有属性变为只读。
   - `Pick<T, K>`：挑选指定键的子集。
   - `Omit<T, K>`：排除指定键。
   - `Record<K, T>`：创建键为 `K`（联合类型），值为 `T` 的类型。

   例如：
   ```typescript
   interface User {
     name: string;
     age: number;
   }

   type OptionalUser = Partial<User>;
   type MandatoryUser = Required<User>;
   type NameOnly = Pick<User, "name">;
   type NoAge = Omit<User, "age">;
   ```

### 实际应用场景
1. **简化类型定义**：
   映射类型可以用来快速生成复杂类型的变体。例如，在 React 组件中，定义 props 的子集：

   ```typescript
   interface Props {
     id: number;
     name: string;
     age: number;
     email: string;
   }

   type PartialProps = Partial<Props>;

   function UserComponent(props: PartialProps) {
     // 可以只传递部分属性
   }
   ```

2. **处理 API 数据**：
   当处理 API 响应时，映射类型可以用来将部分字段设为可选或只读：

   ```typescript
   interface ApiResponse {
     id: number;
     data: { name: string; age: number };
     status: string;
   }

   type ReadonlyResponse = Readonly<ApiResponse>;

   const response: ReadonlyResponse = {
     id: 1,
     data: { name: "Alice", age: 30 },
     status: "success",
   };
   // response.status = "error"; // 错误：只读属性
   ```

3. **动态键的类型安全**：
   映射类型可以用来定义动态键的类型结构。例如，定义一个配置对象：

   ```typescript
   type ConfigKeys = "apiUrl" | "timeout" | "retryCount";

   type Config = {
     [K in ConfigKeys]: K extends "apiUrl" ? string : number;
   };

   const config: Config = {
     apiUrl: "https://api.example.com",
     timeout: 5000,
     retryCount: 3,
   };
   ```

4. **类型安全的工具函数**：
   映射类型常用于编写类型安全的工具函数。例如，一个函数将对象的所有值转换为字符串：

   ```typescript
   type Stringified<T> = {
     [K in keyof T]: string;
   };

   function stringifyValues<T>(obj: T): Stringified<T> {
     const result = {} as Stringified<T>;
     for (const key in obj) {
       if (Object.prototype.hasOwnProperty.call(obj, key)) {
         result[key] = String(obj[key]);
       }
     }
     return result;
   }

   const user = { name: "Alice", age: 30 };
   const stringUser = stringifyValues(user);
   // stringUser: { name: string; age: string }
   console.log(stringUser); // { name: "Alice", age: "30" }
   ```

5. **高级类型操作**：
   映射类型在库开发中非常常见，用于生成复杂的类型。例如，TypeScript 标准库中的 `Partial`、`Pick` 等工具类型都基于映射类型实现。

### 注意事项
1. **与索引类型的区别**：
   - **索引类型**（Index Types）通常指通过索引签名（`[key: string]: Type`）定义动态键的对象类型，强调动态性和灵活性。
   - **映射类型**（Mapped Types）基于已有类型的键（通常通过 `keyof` 或联合类型）生成新类型，强调类型转换和重用。
   - 映射类型可以看作是索引类型的更高级形式，允许更精细的控制。

2. **键的重新映射（`as` 关键字）**：
   从 TypeScript 4.1 开始，映射类型支持通过 `as` 关键字重新映射键。例如：

   ```typescript
   type Getter<T> = {
     [K in keyof T as `get${Capitalize<string & K>}`]: () => T[K];
   };

   interface User {
     name: string;
     age: number;
   }

   type UserGetters = Getter<User>;
   // 等价于：
   // type UserGetters = {
   //   getName: () => string;
   //   getAge: () => number;
   // }
   ```

   这可以将属性名从 `name` 转换为 `getName`，实现更复杂的类型变换。

3. **性能考虑**：
   映射类型在复杂类型操作中可能会导致类型推断性能下降，尤其是在处理大型联合类型或嵌套类型时。应尽量保持类型定义简洁。

4. **类型兼容性**：
   映射类型生成的类型必须与原类型的结构兼容。例如，值的类型不能违反原类型的约束。

   ```typescript
   interface User {
     name: string;
     age: number;
   }

   type Invalid = {
     [K in keyof User]: boolean; // 合法，但改变了值的类型
   };

   const user: Invalid = {
     name: true,
     age: true,
   };
   ```

### 总结
映射类型是 TypeScript 中用于动态创建和转换类型的强大工具，通过遍历键并重新定义属性类型或修饰符，提供高度灵活的类型操作。它们在实际编程中的作用包括简化类型定义、处理动态数据、实现类型安全的工具函数以及支持库开发等。常见的内置工具类型（如 `Partial`、`Readonly`、`Pick`）都是映射类型的应用。开发者需要注意映射类型与索引类型的区别、类型兼容性以及性能问题，以充分发挥其在类型安全和代码复用中的优势。